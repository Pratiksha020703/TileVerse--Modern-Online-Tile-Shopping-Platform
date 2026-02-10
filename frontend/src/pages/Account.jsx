// import { Link } from "react-router-dom";
// import "../styles/Account.css";

// const Account = () => {
//     return (
//         <>
//             {/* Profile Header */}
//             <section className="profile-hero text-white text-center d-flex align-items-center">
//                 <div className="container">
//                     <h1 className="display-4 fw-bold mb-2">My Profile</h1>
//                     <p className="lead">
//                         Manage your personal details, orders, and saved collections
//                     </p>
//                 </div>
//             </section>

//             {/* Profile Content */}
//             <section className="py-5">
//                 <div className="container">
//                     <div className="row">
//                         {/* Left Column */}
//                         <div className="col-md-4 mb-4">
//                             <div className="card shadow-sm border-0 text-center">
//                                 <div className="card-body">
//                                     <div className="profile-avatar mb-3">
//                                         <span className="avatar-circle">JD</span>
//                                     </div>
//                                     <h5 className="fw-bold mb-1">John Doe</h5>
//                                     <p className="text-muted mb-3">
//                                         johndoe@email.com
//                                     </p>
//                                     <Link
//                                         to="#"
//                                         className="btn btn-sm btn-coral-outline"
//                                     >
//                                         Edit Profile
//                                     </Link>
//                                 </div>
//                             </div>
//                         </div>

//                         {/* Right Column */}
//                         <div className="col-md-8">
//                             <div className="row">
//                                 {/* Card 1 */}
//                                 <div className="col-md-6 mb-4">
//                                     <div className="card shadow-sm border-0 h-100">
//                                         <div className="card-body">
//                                             <h5 className="fw-bold text-coral">
//                                                 My Orders
//                                             </h5>
//                                             <p className="card-text">
//                                                 Track your tile orders and delivery status.
//                                             </p>
//                                             <Link
//                                                 to="#"
//                                                 className="btn btn-sm btn-outline-dark"
//                                             >
//                                                 View Orders
//                                             </Link>
//                                         </div>
//                                     </div>
//                                 </div>

//                                 {/* Card 2 */}
//                                 <div className="col-md-6 mb-4">
//                                     <div className="card shadow-sm border-0 h-100">
//                                         <div className="card-body">
//                                             <h5 className="fw-bold text-coral">
//                                                 Saved Designs
//                                             </h5>
//                                             <p className="card-text">
//                                                 Your favorite tiles and inspiration boards.
//                                             </p>
//                                             <Link
//                                                 to="#"
//                                                 className="btn btn-sm btn-outline-dark"
//                                             >
//                                                 View Saved
//                                             </Link>
//                                         </div>
//                                     </div>
//                                 </div>

//                                 {/* Card 3 */}
//                                 <div className="col-md-6 mb-4">
//                                     <div className="card shadow-sm border-0 h-100">
//                                         <div className="card-body">
//                                             <h5 className="fw-bold text-coral">
//                                                 Address Book
//                                             </h5>
//                                             <p className="card-text">
//                                                 Manage delivery and billing addresses.
//                                             </p>
//                                             <Link
//                                                 to="#"
//                                                 className="btn btn-sm btn-outline-dark"
//                                             >
//                                                 Manage
//                                             </Link>
//                                         </div>
//                                     </div>
//                                 </div>

//                                 {/* Card 4 */}
//                                 <div className="col-md-6 mb-4">
//                                     <div className="card shadow-sm border-0 h-100">
//                                         <div className="card-body">
//                                             <h5 className="fw-bold text-coral">
//                                                 Support
//                                             </h5>
//                                             <p className="card-text">
//                                                 Need help choosing tiles or tracking orders?
//                                             </p>
//                                             <Link
//                                                 to="#"
//                                                 className="btn btn-sm btn-outline-dark"
//                                             >
//                                                 Contact Us
//                                             </Link>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>
//                         </div>
//                     </div>
//                 </div>
//             </section>
//         </>
//     );
// };

// export default Account;


import { Link, useNavigate } from "react-router-dom";
import "../styles/Account.css";
import { getUserFromToken } from "../utils/auth";

const Account = () => {
  const user = getUserFromToken();
  const navigate = useNavigate();

  // fallback initials
  const initials = user?.fullName
    ? user.fullName
        .split(" ")
        .map((n) => n[0])
        .join("")
        .toUpperCase()
    : "U";

  return (
    <>
      {/* Profile Header */}
      <section className="profile-hero text-white text-center d-flex align-items-center">
        <div className="container">
          <h1 className="display-4 fw-bold mb-2">My Profile</h1>
          <p className="lead">
            Manage your personal details, orders, and saved collections
          </p>
        </div>
      </section>

      {/* Profile Content */}
      <section className="py-5">
        <div className="container">
          <div className="row">
            {/* Left Column */}
            <div className="col-md-4 mb-4">
              <div className="card shadow-sm border-0 text-center">
                <div className="card-body">
                  <div className="profile-avatar mb-3">
                    <span className="avatar-circle">{initials}</span>
                  </div>

                  {/* Real Name */}
                  <h5 className="fw-bold mb-1">
                    {user?.fullName || "User"}
                  </h5>

                  {/* Real Email */}
                  <p className="text-muted mb-3">
                    {user?.email}
                  </p>

                  {/* <Link to="/editprofile" className="btn btn-sm btn-coral-outline">
                    Edit Profile
                  </Link> */}
                </div>
              </div>
            </div>

            {/* Right Column */}
            <div className="col-md-8">
              <div className="row">

                {/* MY ORDERS */}
                <div className="col-md-6 mb-4">
                  <div className="card shadow-sm border-0 h-100">
                    <div className="card-body">
                      <h5 className="fw-bold text-coral">My Orders</h5>
                      <p>Track your tile orders and delivery status.</p>
                      <button
                        className="btn btn-sm btn-outline-dark"
                        onClick={() => navigate("/vieworders")}
                      >
                        View Orders
                      </button>
                    </div>
                  </div>
                </div>

                {/* WISHLIST */}
                <div className="col-md-6 mb-4">
                  <div className="card shadow-sm border-0 h-100">
                    <div className="card-body">
                      <h5 className="fw-bold text-coral">Saved Designs</h5>
                      <p>Your favorite tiles and inspiration boards.</p>
                      <Link to="/wishlist" className="btn btn-sm btn-outline-dark">
                        View Saved
                      </Link>
                    </div>
                  </div>
                </div>

                {/* ADDRESS BOOK */}
                <div className="col-md-6 mb-4">
                  <div className="card shadow-sm border-0 h-100">
                    <div className="card-body">
                      <h5 className="fw-bold text-coral">Address Book</h5>
                      <p>Manage delivery and billing addresses.</p>
                      <button
                        className="btn btn-sm btn-outline-dark"
                        onClick={() => navigate("/address")}
                      >
                        Manage
                      </button>
                    </div>
                  </div>
                </div>

                {/* SUPPORT */}
                <div className="col-md-6 mb-4">
                  <div className="card shadow-sm border-0 h-100">
                    <div className="card-body">
                      <h5 className="fw-bold text-coral">Support</h5>
                      <p>Need help choosing tiles or tracking orders?</p>
                      <Link to="/contactus" className="btn btn-sm btn-outline-dark">
                        Contact Us
                      </Link>
                    </div>
                  </div>
                </div>

              </div>
            </div>

          </div>
        </div>
      </section>
    </>
  );
};

export default Account;
