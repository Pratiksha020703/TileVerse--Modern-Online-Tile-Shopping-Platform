using System;
using System.Security.Cryptography;
using System.Threading.Tasks;
using AuthService.Data;
using AuthService.DTOs;
using AuthService.Models;
using AuthService.Services;
using BCrypt.Net;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Net;


namespace AuthService.Controllers
{
    [ApiController]
    [Route("api/auth")]
    public class AuthController : ControllerBase
    {
        private readonly AuthDbContext _context;
        private readonly JwtService _jwtService;
        private readonly EmailService _emailService;

        public AuthController(AuthDbContext context, JwtService jwtService, EmailService emailService)
        {
            _context = context;
            _jwtService = jwtService;
            _emailService = emailService;
        }


        [HttpPost("register")]
        public async Task<IActionResult> Register(RegisterRequest request)
        {
            if (await _context.Users.AnyAsync(u => u.Email == request.Email))
                return BadRequest("Email already exists");

            string role = request.Email.ToLower() == "admin@gmail.com"
                ? "ADMIN"
                : "CUSTOMER";

            var user = new User
            {
                FullName = request.FullName,
                Email = request.Email,
                PasswordHash = BCrypt.Net.BCrypt.HashPassword(request.Password),
                Role = role
            };

            _context.Users.Add(user);
            await _context.SaveChangesAsync();

            return Ok("Registered successfully");
        }

        [HttpPost("login")]
        public async Task<IActionResult> Login(LoginRequest request)
        {
            var user = await _context.Users.FirstOrDefaultAsync(u => u.Email == request.Email);

            if (user == null || !BCrypt.Net.BCrypt.Verify(request.Password, user.PasswordHash))
                return Unauthorized("Invalid credentials");

            var token = _jwtService.GenerateToken(
                user.Id,
                user.FullName,
                user.Email,
                user.Role
            );

            return Ok(new { token });
        }

        [HttpPost("forgot-password")]
        public async Task<IActionResult> ForgotPassword([FromBody] ForgotPasswordRequest request)
        {
            var user = await _context.Users.FirstOrDefaultAsync(u => u.Email == request.Email);

            if (user == null)
                return Ok("If email exists, reset link will be sent");

            var token = Convert.ToBase64String(RandomNumberGenerator.GetBytes(64));

            user.ResetToken = token;
            user.ResetTokenExpiry = DateTime.UtcNow.AddMinutes(15);

            await _context.SaveChangesAsync();

           var encodedToken = WebUtility.UrlEncode(token);

            var resetLink = $"http://localhost:3000/reset-password/{encodedToken}";

            await _emailService.SendEmailAsync(
                user.Email,
                "TileVerse Password Reset",
                $"Click to reset password: {resetLink}"
            );

            return Ok("Reset link sent");
        }

        [HttpPost("reset-password")]
        public async Task<IActionResult> ResetPassword([FromBody] ResetPasswordRequest request)
        {
            var user = await _context.Users.FirstOrDefaultAsync(u =>
                u.ResetToken == request.Token &&
                u.ResetTokenExpiry.HasValue &&
                u.ResetTokenExpiry.Value > DateTime.UtcNow
            );

            if (user == null)
                return BadRequest("Invalid or expired token");

            user.PasswordHash = BCrypt.Net.BCrypt.HashPassword(request.NewPassword);
            user.ResetToken = null;
            user.ResetTokenExpiry = null;

            await _context.SaveChangesAsync();

            return Ok("Password reset successful");
        }


    }
}


