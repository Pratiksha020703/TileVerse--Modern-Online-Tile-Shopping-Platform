using System.ComponentModel.DataAnnotations;

namespace AuthService.DTOs
{
    public class ResetPasswordRequest
    {
        [Required]
        public string Token { get; set; } = null!;

        [Required, MinLength(6)]
        public string NewPassword { get; set; } = null!;
    }
}
