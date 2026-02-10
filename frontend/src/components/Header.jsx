// import { Link, NavLink } from "react-router-dom";
// import "../styles/Navbar.css";
// import Logo from "../images/Grayscale_Transparent_NoBuffer (1).png";

// const Header = () => {
//     return (
//         <header className="navbar navbar-expand-lg navbar-light bg-light">
//             <div className="container-fluid">

//                 {/* Logo */}
//                 <Link className="navbar-brand fw-bold" to="/">
//                     <img
//                         src={Logo}
//                         alt="Tileverse Logo"
//                     />
//                 </Link>

//                 {/* Mobile Toggle */}
//                 <button
//                     className="navbar-toggler"
//                     type="button"
//                     data-bs-toggle="collapse"
//                     data-bs-target="#navbarNav"
//                     aria-controls="navbarNav"
//                     aria-expanded="false"
//                     aria-label="Toggle navigation"
//                 >
//                     <span className="navbar-toggler-icon"></span>
//                 </button>

//                 {/* Navbar Content */}
//                 <div className="collapse navbar-collapse" id="navbarNav">

//                     {/* Search */}
//                     <input
//                         className="form-control navbar-search"
//                         type="search"
//                         placeholder="Search"
//                         aria-label="Search"
//                     />

//                     {/* Nav Links */}
//                     <ul className="navbar-nav ms-auto align-items-lg-center">
//                         <li className="nav-item">
//                             <NavLink className="nav-link" to="/">
//                                 Home
//                             </NavLink>
//                         </li>

//                         <li className="nav-item">
//                             <NavLink className="nav-link" to="/products">
//                                 Products
//                             </NavLink>
//                         </li>

//                         <li className="nav-item">
//                             <NavLink className="nav-link" to="/gallery">
//                                 Gallery
//                             </NavLink>
//                         </li>

//                         <li className="nav-item">
//                             <NavLink className="nav-link" to="/account">
//                                 Account
//                             </NavLink>
//                         </li>

//                         {/* Buttons */}
//                         {/* <li className="nav-item ms-lg-2">
//                             <button className="btn btn-sm btn-outline-dark w-100">
//                                 Sign In
//                             </button>
//                         </li>

//                         <li className="nav-item ms-lg-2">
//                             <button className="btn btn-sm btn-coral w-100">
//                                 Sign Up
//                             </button>
//                         </li> */}
//                         <li className="nav-item ms-lg-2">
//                             <Link to="/auth" className="btn btn-sm btn-outline-dark w-100">
//                                 Sign In
//                             </Link>
//                         </li>

//                         <li className="nav-item ms-lg-2">
//                             <Link to="#" className="btn btn-sm btn-coral w-100">
//                                 Add to cart
//                             </Link>
//                         </li>
//                     </ul>

//                 </div>
//             </div>
//         </header>
//     );
// };

// export default Header;

import { Link, NavLink } from "react-router-dom";
import { getUserFromToken, logout } from "../utils/auth";
import "../styles/Navbar.css";
import Logo from "../images/Grayscale_Transparent_NoBuffer (1).png";

const Header = () => {
  const user = getUserFromToken();

  // Extract name safely
  const displayName = user?.fullName? user.fullName: user?.email? user.email.split("@")[0]: " ";

  return (
    <header className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container-fluid">

        {/* Logo */}
        <Link className="navbar-brand fw-bold" to="/">
          <img src={Logo} alt="Tileverse Logo" />
        </Link>

        <div className="collapse navbar-collapse">

          <ul className="navbar-nav ms-auto align-items-lg-center">
            <li className="nav-item">
              <NavLink className="nav-link" to="/">Home</NavLink>
            </li>

            <li className="nav-item">
              <NavLink className="nav-link" to="/products">Products</NavLink>
            </li>

            <li className="nav-item">
              <NavLink className="nav-link" to="/gallery">Gallery</NavLink>
            </li>

            {user ? (
              <li className="nav-item dropdown">
                <button
                  className="btn btn-outline-dark dropdown-toggle"
                  data-bs-toggle="dropdown"
                >
                  👤 {displayName}
                </button>

                <ul className="dropdown-menu dropdown-menu-end">
                  <li>
                    <Link className="dropdown-item" to="/account">
                      My Account
                    </Link>
                  </li>
                  <li>
                    <button className="dropdown-item text-danger" onClick={logout}>
                      Logout
                    </button>
                  </li>
                </ul>
              </li>
            ) : (
              <li className="nav-item ms-lg-2">
                <Link to="/auth" className="btn btn-outline-dark">
                  Sign In
                </Link>
              </li>
            )}

            <li className="nav-item ms-lg-2">
                <Link to="/cart" className="btn btn-sm btn-coral w-100">
                🛒 Cart
                </Link>
            </li>
          </ul>

        </div>
      </div>
    </header>
  );
};

export default Header;
