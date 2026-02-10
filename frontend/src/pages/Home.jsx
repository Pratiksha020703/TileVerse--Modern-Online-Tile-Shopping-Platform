// import { Link } from "react-router-dom";
// import "../styles/Home.css";

// const Home = () => {
//     return (
//         <>
//             {/* Hero Section */}
//             <section className="hero-section text-center text-white d-flex align-items-center justify-content-center">
//                 <div className="container">
//                     <h1 className="display-2 fw-bold mb-3">
//                         Style Your Space. Live Your Dream.
//                     </h1>
//                     <p className="lead mb-4">
//                         Explore our curated collection of modern and timeless tiles for every room.
//                     </p>
//                     <a
//                         href="#products"
//                         className="btn btn-lg btn-hero-white me-2"
//                     >
//                         Shop Tiles Now
//                     </a>
//                     <Link
//                         to="/gallery"
//                         className="btn btn-lg btn-light-outline"
//                     >
//                         View Gallery
//                     </Link>
//                 </div>
//             </section>

//             {/* Products Section */}
//             <section id="products" className="py-5">
//                 <div className="container">
//                     <h2 className="text-center mb-5 fw-bold">
//                         Explore Our Collections
//                     </h2>

//                     <div className="row text-center">
//                         {/* Card 1 */}
//                         <div className="col-md-4 mb-4">
//                             <div className="card shadow-sm border-0 h-100">
//                                 <div className="card-body">
//                                     <h5 className="card-title fw-bold text-coral">
//                                         Kitchen Tiles
//                                     </h5>
//                                     <p className="card-text">
//                                         Durable and stylish backsplashes and flooring options.
//                                     </p>
//                                     <Link to="#" className="btn btn-sm btn-outline-dark mt-2">
//                                         View
//                                     </Link>
//                                 </div>
//                             </div>
//                         </div>

//                         {/* Card 2 */}
//                         <div className="col-md-4 mb-4">
//                             <div className="card shadow-sm border-0 h-100">
//                                 <div className="card-body">
//                                     <h5 className="card-title fw-bold text-coral">
//                                         Bathroom Tiles
//                                     </h5>
//                                     <p className="card-text">
//                                         Water-resistant porcelain and ceramic for a spa feel.
//                                     </p>
//                                     <Link to="#" className="btn btn-sm btn-outline-dark mt-2">
//                                         View
//                                     </Link>
//                                 </div>
//                             </div>
//                         </div>

//                         {/* Card 3 */}
//                         <div className="col-md-4 mb-4">
//                             <div className="card shadow-sm border-0 h-100">
//                                 <div className="card-body">
//                                     <h5 className="card-title fw-bold text-coral">
//                                         Outdoor Paving
//                                     </h5>
//                                     <p className="card-text">
//                                         Frost-proof and anti-slip tiles for patios and gardens.
//                                     </p>
//                                     <Link to="#" className="btn btn-sm btn-outline-dark mt-2">
//                                         View
//                                     </Link>
//                                 </div>
//                             </div>
//                         </div>
//                     </div>
//                 </div>
//             </section>

//             {/* CTA Section */}
//             <section className="cta-section py-5 text-white text-center">
//                 <div className="container">
//                     <h2 className="mb-3 fw-bold">
//                         Ready to Start Your Project?
//                     </h2>
//                     <p className="lead mb-4">
//                         Get professional design advice and free samples shipped right to your door.
//                     </p>
//                     <Link to="/contactus" className="btn btn-lg btn-light fw-bold">
//                         Request Samples
//                     </Link>
//                 </div>
//             </section>
//         </>
//     );
// };

// export default Home;



import { Link } from "react-router-dom";
import "../styles/Home.css";

const Home = () => {
    return (
        <>
            {/* Hero Section */}
            <section className="hero-section text-center text-white d-flex align-items-center justify-content-center">
                <div className="container">
                    <h1 className="display-2 fw-bold mb-3">
                        Style Your Space. Live Your Dream.
                    </h1>
                    <p className="lead mb-4">
                        Explore our curated collection of modern and timeless tiles for every room.
                    </p>
                    <a
                        href="#products"
                        className="btn btn-lg btn-hero-white me-2"
                    >
                        Shop Tiles Now
                    </a>
                    <Link
                        to="/gallery"
                        className="btn btn-lg btn-light-outline"
                    >
                        View Gallery
                    </Link>
                </div>
            </section>

            {/* Products Section */}
            <section id="products" className="py-5">
                <div className="container">
                    <h2 className="text-center mb-5 fw-bold">
                        Explore Our Collections
                    </h2>

                    <div className="row text-center">
                        {/* Card 1 */}
                        <div className="col-md-4 mb-4">
                            <div className="card shadow-sm border-0 h-100">
                                <div className="card-body">
                                    <h5 className="card-title fw-bold text-coral">
                                        Kitchen Tiles
                                    </h5>
                                    <p className="card-text">
                                        Durable and stylish backsplashes and flooring options.
                                    </p>

                                    {/* ONLY this line changed */}
                                    <Link to="/gallery?category=kitchen" className="btn btn-sm btn-outline-dark mt-2">
                                        View
                                    </Link>
                                </div>
                            </div>
                        </div>

                        {/* Card 2 */}
                        <div className="col-md-4 mb-4">
                            <div className="card shadow-sm border-0 h-100">
                                <div className="card-body">
                                    <h5 className="card-title fw-bold text-coral">
                                        Bathroom Tiles
                                    </h5>
                                    <p className="card-text">
                                        Water-resistant porcelain and ceramic for a spa feel.
                                    </p>

                                    {/* ONLY this line changed */}
                                    <Link to="/gallery?category=bathroom" className="btn btn-sm btn-outline-dark mt-2">
                                        View
                                    </Link>
                                </div>
                            </div>
                        </div>

                        {/* Card 3 */}
                        <div className="col-md-4 mb-4">
                            <div className="card shadow-sm border-0 h-100">
                                <div className="card-body">
                                    <h5 className="card-title fw-bold text-coral">
                                        Outdoor Paving
                                    </h5>
                                    <p className="card-text">
                                        Frost-proof and anti-slip tiles for patios and gardens.
                                    </p>

                                    {/* ONLY this line changed */}
                                    <Link to="/gallery?category=outdoor" className="btn btn-sm btn-outline-dark mt-2">
                                        View
                                    </Link>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </section>

            {/* CTA Section */}
            <section className="cta-section py-5 text-white text-center">
                <div className="container">
                    <h2 className="mb-3 fw-bold">
                        Ready to Start Your Project?
                    </h2>
                    <p className="lead mb-4">
                        Get professional design advice and free samples shipped right to your door.
                    </p>
                    <Link to="/contactus" className="btn btn-lg btn-light fw-bold">
                        Request Samples
                    </Link>
                </div>
            </section>
        </>
    );
};

export default Home;
