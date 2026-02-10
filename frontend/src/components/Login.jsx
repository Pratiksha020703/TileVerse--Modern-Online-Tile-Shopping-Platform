// import React from "react";
// import "./Login.css";
// import { useState } from "react";

// export default function Login() {
//   return (
//     <div>
//       <div class="container" id="container">
//         <div class="form-container sign-up-container">
//           <form action="#">
//             <h1>Create Account</h1>
//             <div class="social-container">
//               <a href="#" class="social">
//                 <i class="fab fa-facebook-f"></i>
//               </a>
//               <a href="#" class="social">
//                 <i class="fab fa-google-plus-g"></i>
//               </a>
//               <a href="#" class="social">
//                 <i class="fab fa-linkedin-in"></i>
//               </a>
//             </div>
//             <span>or use your email for registration</span>
//             <input type="text" placeholder="Name" />
//             <input type="email" placeholder="Email" />
//             <input type="password" placeholder="Password" />
//             <button>Sign Up</button>
//           </form>
//         </div>
//         <div class="form-container sign-in-container">
//           <form action="#">
//             <h1>Sign in</h1>
//             <div class="social-container">
//               <a href="#" class="social">
//                 <i class="fab fa-facebook-f"></i>
//               </a>
//               <a href="#" class="social">
//                 <i class="fab fa-google-plus-g"></i>
//               </a>
//               <a href="#" class="social">
//                 <i class="fab fa-linkedin-in"></i>
//               </a>
//             </div>
//             <span>or use your account</span>
//             <input type="email" placeholder="Email" />
//             <input type="password" placeholder="Password" />
//             <a href="#">Forgot your password?</a>
//             <a href="#">
//               <button>Sign In</button>
//             </a>
//           </form>
//         </div>
//         <div class="overlay-container">
//           <div class="overlay">
//             <div class="overlay-panel overlay-left">
//               <img
//                 src="Images/FullLogo_Transparent.png"
//                 alt=""
//                 margin="auto"
//                 width="250px"
//               />
//               <h1>Welcome Back!</h1>
//               <p>
//                 To keep connected with us please login with your personal info
//               </p>
//               <button class="ghost" id="signIn">
//                 Sign In
//               </button>
//             </div>
//             <div class="overlay-panel overlay-right">
//               <img
//                 src="Images/FullLogo_Transparent.png"
//                 alt=""
//                 margin="auto"
//                 width="250px"
//               />
//               <h1 class="h1color">Hello, Friend!</h1>
//               <p>Enter your personal details and start journey with us</p>
//               <button class="ghost" id="signUp">
//                 Sign Up
//               </button>
//             </div>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// }




// import React, { useState } from "react";
// // import "../styles/Login.css";
// import Logo from "../images/FullLogo_Transparent.png";
// import { Link } from "react-router-dom";

// export default function Login() {
//   const [rightPanelActive, setRightPanelActive] = useState(false);

//   const handleSignUp = () => setRightPanelActive(true);
//   const handleSignIn = () => setRightPanelActive(false);

//   return (
//     <div className="login-wrapper">
//       <div className={`container ${rightPanelActive ? "right-panel-active" : ""}`}>
//         {/* Sign Up Form */}
//         <div className="form-container sign-up-container">
//           <form>
//             <h1>Create Account</h1>
//             <div className="social-container">
//               <Link to="#" className="social"><i className="fab fa-facebook-f"></i></Link>
//               <Link to="#" className="social"><i className="fab fa-google-plus-g"></i></Link>
//               <Link to="#" className="social"><i className="fab fa-linkedin-in"></i></Link>
//             </div>
//             <span>or use your email for registration</span>
//             <input type="text" placeholder="Name" />
//             <input type="email" placeholder="Email" />
//             <input type="password" placeholder="Password" />
//             <button type="button">Sign Up</button>
//           </form>
//         </div>

//         {/* Sign In Form */}
//         <div className="form-container sign-in-container">
//           <form>
//             <h1>Sign in</h1>
//             <div className="social-container">
//               <Link to="#" className="social"><i className="fab fa-facebook-f"></i></Link>
//               <Link to="#" className="social"><i className="fab fa-google-plus-g"></i></Link>
//               <Link to="#" className="social"><i className="fab fa-linkedin-in"></i></Link>
//             </div>
//             <span>or use your account</span>
//             <input type="email" placeholder="Email" />
//             <input type="password" placeholder="Password" />
//             <Link to="#">Forgot your password?</Link>
//             <button type="button">Sign In</button>
//           </form>
//         </div>

//         {/* Overlay */}
//         <div className="overlay-container">
//           <div className="overlay">
//             <div className="overlay-panel overlay-left">
//               <img src={Logo} alt="Logo" width="250" />
//               <h1>Welcome Back!</h1>
//               <p>To keep connected with us please login with your personal info</p>
//               <button className="ghost" onClick={handleSignIn}>Sign In</button>
//             </div>
//             <div className="overlay-panel overlay-right">
//               <img src={Logo} alt="Logo" width="250" />
//               <h1 className="h1color">Hello, Friend!</h1>
//               <p>Enter your personal details and start journey with us</p>
//               <button className="ghost" onClick={handleSignUp}>Sign Up</button>
//             </div>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// }

import { registerUser, loginUser } from "../api/authApi";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";



const AuthForm = () => {
  const navigate = useNavigate();
  const [isRightPanelActive, setIsRightPanelActive] = useState(false);
  const [showLoginPassword, setShowLoginPassword] = useState(false);
const [showSignUpPassword, setShowSignUpPassword] = useState(false);


  /* ================= FORM STATES ================= */
  const [loginData, setLoginData] = useState({
    email: "",
    password: "",
  });

  const [registerData, setRegisterData] = useState({
    fullName: "",
    email: "",
    password: "",
  });

  /* ================= LOGIN ================= */
  const handleLogin = async () => {
    console.log("LOGIN PAYLOAD:", loginData);
    try {
      const res = await loginUser(loginData);
      localStorage.setItem("token", res.data.token);
      
      const payload = JSON.parse(atob(res.data.token.split(".")[1]));

      const role = payload["http://schemas.microsoft.com/ws/2008/06/identity/claims/role"];
      alert("Login successful ✅");
      //navigate("/");
      if (role === "ADMIN") {
        navigate("/admin");
      } else {
        navigate("/");
      }

    } catch (err) {
        console.error("API ERROR:", err);
        console.error("RESPONSE:", err.response);
        alert(
          err.response?.data?.message ||
          err.response?.data ||
          err.message ||
          "API failed"
        );
      }

  };

  /* ================= REGISTER ================= */
  const handleRegister = async () => {
    console.log("REGISTER PAYLOAD:", registerData);
    try {
      await registerUser(registerData);
      alert("Registration successful ✅");
      setIsRightPanelActive(false);
    } catch (err) {
  console.error("API ERROR:", err);
  console.error("RESPONSE:", err.response);
  alert(
    err.response?.data?.message ||
    err.response?.data ||
    err.message ||
    "API failed"
  );
}

  };

  return (
    <>
      {/* YOUR EXISTING STYLE BLOCK – UNCHANGED */}
      <style>{` @import url("https://fonts.googleapis.com/css?family=Montserrat:400,800");

  * {
    box-sizing: border-box;
  }

  body {
    /* background: #f6f5f7; */
    background-image: url("..\images\Background-Image.jpeg");
    /* backdrop-filter: blur(5px); */
    background-size: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    font-family: "Montserrat", sans-serif;
    height: 100vh;
    margin: -20px 0 50px;
  }

  h1 {
    font-weight: bold;
    /* color: #111111; */
    margin: 0;
  }

  h2 {
    text-align: center;
  }

  p {
    font-size: 14px;
    font-weight: 100;
    line-height: 20px;
    /* color: #111111; */
    letter-spacing: 0.5px;
    margin: 20px 0 30px;
  }

  span {
    font-size: 12px;
  }

  a {
    color: #333;
    font-size: 14px;
    text-decoration: none;
    margin: 15px 0;
  }

  button {
    border-radius: 20px;
    border: 1px solid #ff4b2b;
    /* border: 1px solid black; */
    background-color: #ff4b2b;
    /* background-color: solid darkgray; */
    color: #ffffff;
    /* color: solid black; */
    font-size: 12px;
    font-weight: bold;
    padding: 12px 45px;
    letter-spacing: 1px;
    text-transform: uppercase;
    transition: transform 80ms ease-in;
  }

  button:active {
    transform: scale(0.95);
  }

  button:focus {
    outline: none;
  }

  button.ghost {
    /* background-color: transparent; */
    border: white;
    background-color: white;
    /* text-decoration: black; */
    /* border-color: #111111; */
    color: black;
  }

  form {
    background-color: #ffffff;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    padding: 0 50px;
    height: 100%;
    text-align: center;
  }

  input {
    background-color: #eee;
    border: none;
    padding: 12px 15px;
    margin: 8px 0;
    width: 100%;
  }

  .container {
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
    position: relative;
    overflow: hidden;
    width: 768px;
    max-width: 100%;
    min-height: 480px;
  }

  .form-container {
    position: absolute;
    top: 0;
    height: 100%;
    transition: all 0.6s ease-in-out;
  }

  .sign-in-container {
    left: 0;
    width: 50%;
    z-index: 2;
  }

  .container.right-panel-active .sign-in-container {
    transform: translateX(100%);
  }

  .sign-up-container {
    left: 0;
    width: 50%;
    opacity: 0;
    z-index: 1;
  }

  .container.right-panel-active .sign-up-container {
    transform: translateX(100%);
    opacity: 1;
    z-index: 5;
    animation: show 0.6s;
  }

  @keyframes show {

    0%,
    49.99% {
      opacity: 0;
      z-index: 1;
    }

    50%,
    100% {
      opacity: 1;
      z-index: 5;
    }
  }

  .overlay-container {
    position: absolute;
    top: 0;
    left: 50%;
    width: 50%;
    height: 100%;
    overflow: hidden;
    transition: transform 0.6s ease-in-out;
    z-index: 100;
  }

  .container.right-panel-active .overlay-container {
    transform: translateX(-100%);
  }

  .overlay {
    background: #ff416c;
    background: -webkit-linear-gradient(to right, #ff4b2b, #ff416c);
    background: linear-gradient(to right, #ff4b2b, #ff416c);
    /* background: ; */
    background-repeat: no-repeat;
    background-size: cover;
    background-position: 0 0;
    color: #ffffff;
    position: relative;
    left: -100%;
    height: 100%;
    width: 200%;
    transform: translateX(0);
    transition: transform 0.6s ease-in-out;
  }

  .container.right-panel-active .overlay {
    transform: translateX(50%);
  }

  .overlay-panel {
    position: absolute;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    padding: 0 40px;
    text-align: center;
    top: 0;
    height: 100%;
    width: 50%;
    transform: translateX(0);
    transition: transform 0.6s ease-in-out;
  }

  .overlay-left {
    transform: translateX(-20%);
  }

  .container.right-panel-active .overlay-left {
    transform: translateX(0);
  }

  .overlay-right {
    right: 0;
    transform: translateX(0);
  }

  .container.right-panel-active .overlay-right {
    transform: translateX(20%);
  }

  .social-container {
    margin: 20px 0;
  }

  .social-container a {
    border: 1px solid #dddddd;
    border-radius: 50%;
    display: inline-flex;
    justify-content: center;
    align-items: center;
    margin: 0 5px;
    height: 40px;
    width: 40px;
  }

  footer {
    background-color: #222;
    color: #fff;
    font-size: 14px;
    bottom: 0;
    position: fixed;
    left: 0;
    right: 0;
    text-align: center;
    z-index: 999;
  }

  footer p {
    margin: 10px 0;
  }

  footer i {
    color: red;
  }

  footer a {
    color: #3c97bf;
    text-decoration: none;
  }`}</style>
      

      <div className={`container ${isRightPanelActive ? "right-panel-active" : ""}`}>
        {/* ================= SIGN UP ================= */}
        <div className="form-container sign-up-container">
          <form onSubmit={(e) => e.preventDefault()}>
            <h1>Create Account</h1>

            <span>or use your email for registration</span>

            <input
              type="text"
              placeholder="Name"
              value={registerData.fullName}
              onChange={(e) =>
                setRegisterData({ ...registerData, fullName: e.target.value })
              }
            />

            <input
              type="email"
              placeholder="Email"
              value={registerData.email}
              onChange={(e) =>
                setRegisterData({ ...registerData, email: e.target.value })
              }
            />

            <input
              type={showSignUpPassword ? "text" : "password"}
              placeholder="Password"
              value={registerData.password}
              onChange={(e) =>
                setRegisterData({ ...registerData, password: e.target.value })
              }
            />

            <span
              style={{ cursor: "pointer", fontSize: "12px" }}
              onClick={() => setShowSignUpPassword(!showSignUpPassword)}
            >
              {showSignUpPassword ? "Hide Password" : "Show Password"}
            </span>



            <button type="button" onClick={handleRegister}>
              Sign Up
            </button>
          </form>
        </div>

        {/* ================= SIGN IN ================= */}
        <div className="form-container sign-in-container">
          <form onSubmit={(e) => e.preventDefault()}>
            <h1>Sign in</h1>

            <span>or use your account</span>

            <input
              type="email"
              placeholder="Email"
              value={loginData.email}
              onChange={(e) =>
                setLoginData({ ...loginData, email: e.target.value })
              }
            />

            <input
              type={showLoginPassword ? "text" : "password"}
              placeholder="Password"
              value={loginData.password}
              onChange={(e) =>
                setLoginData({ ...loginData, password: e.target.value })
              }
            />

            <span
            style={{ cursor: "pointer", fontSize: "12px" }}
            onClick={() => setShowLoginPassword(!showLoginPassword)}
          >
            {showLoginPassword ? "Hide Password" : "Show Password"}
          </span>


            <Link to="/forgot-password">Forgot your password?</Link>

            <button type="button" onClick={handleLogin}>
              Sign In
            </button>
          </form>
        </div>

        {/* ================= OVERLAY ================= */}
        <div className="overlay-container">
          <div className="overlay">
            <div className="overlay-panel overlay-left">
              <h1>Welcome Back!</h1>
              <p>Please login with your personal info</p>
              <button className="ghost" onClick={() => setIsRightPanelActive(false)}>
                Sign In
              </button>
            </div>

            <div className="overlay-panel overlay-right">
              <h1>Hello, Friend!</h1>
              <p>Enter your details and start your journey</p>
              <button className="ghost" onClick={() => setIsRightPanelActive(true)}>
                Sign Up
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default AuthForm;
