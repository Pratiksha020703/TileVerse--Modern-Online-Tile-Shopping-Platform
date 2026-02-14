// using AuthService.Data;
// using AuthService.Services;
// using Microsoft.EntityFrameworkCore;

// var builder = WebApplication.CreateBuilder(args);

// // ✅ MySQL connection string (LOCAL / Docker MySQL)
// var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");

// // ✅ EF Core + MySQL
// builder.Services.AddDbContext<AuthDbContext>(options =>
//     options.UseMySql(connectionString, ServerVersion.AutoDetect(connectionString))
// );

// // ✅ Services
// builder.Services.AddScoped<JwtService>();

// builder.Services.AddControllers();
// builder.Services.AddEndpointsApiExplorer();
// builder.Services.AddSwaggerGen();
// builder.Services.AddCors(options =>
// {
//     options.AddPolicy("AllowFrontend",
//         policy =>
//         {
//             policy
//                 .WithOrigins("http://localhost:3000")
//                 .AllowAnyHeader()
//                 .AllowAnyMethod();
//         });
// });


// var app = builder.Build();
                                                                                    
// // ✅ Auto-create DB tables (DEV only)
// using (var scope = app.Services.CreateScope())
// {
//     var db = scope.ServiceProvider.GetRequiredService<AuthDbContext>();
//     db.Database.EnsureCreated();
// }

// app.UseSwagger();
// app.UseSwaggerUI();

// app.UseAuthorization();
// app.UseCors("AllowFrontend");

// app.MapControllers();

// app.Run();


using AuthService.Data;
using AuthService.Services;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);


// ===== Read connection string directly from Docker / appsettings =====
var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");


// ===== EF Core + MySQL =====
builder.Services.AddDbContext<AuthDbContext>(options =>
    options.UseMySql(
        connectionString,
        new MySqlServerVersion(new Version(8, 0, 36)),
        mySqlOptions => mySqlOptions.EnableRetryOnFailure()
        )
);


// ===== Services =====
builder.Services.AddScoped<JwtService>();
builder.Services.AddScoped<EmailService>();

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();


// ===== CORS =====
// builder.Services.AddCors(options =>
// {
//     options.AddPolicy("AllowFrontend",
//         policy =>
//         {
//             policy
//                 .WithOrigins("http://localhost:3000")
//                 .AllowAnyHeader()
//                 .AllowAnyMethod();
//         });
// });

builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowFrontend",
        policy =>
        {
            policy
                .WithOrigins(
                    "http://localhost:3000",
                    "http://127.0.0.1:3000"
                )
                .AllowAnyHeader()
                .AllowAnyMethod()
                .AllowCredentials();
        });
});

var app = builder.Build();


// ===== Auto-create DB tables =====
using (var scope = app.Services.CreateScope())
{
    var db = scope.ServiceProvider.GetRequiredService<AuthDbContext>();
    db.Database.EnsureCreated();
}


app.UseSwagger();
app.UseSwaggerUI();

app.UseCors("AllowFrontend");
app.UseAuthentication();

app.UseAuthorization();

app.MapControllers();

app.Run();
