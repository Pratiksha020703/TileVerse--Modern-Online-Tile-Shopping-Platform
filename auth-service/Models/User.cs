using System;
using System.ComponentModel.DataAnnotations;

namespace AuthService.Models
{
    public class User
    {
        [Key] // ✅ PRIMARY KEY
        public int Id { get; set; }

        [Required]
        public string FullName { get; set; } = string.Empty;

        [Required,EmailAddress]
        public string Email { get; set; } = string.Empty;

        [Required]
        public string PasswordHash { get; set; } = string.Empty;

        public string? Role { get; set; } = string.Empty;

        // For Forgot Password
        public string? ResetToken { get; set; }
        public DateTime? ResetTokenExpiry { get; set; }

        public DateTime CreatedAt { get; set; } = DateTime.UtcNow;
    }
}
