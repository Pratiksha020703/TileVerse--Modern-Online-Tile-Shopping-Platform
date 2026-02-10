import { Link } from "react-router-dom";

const Footer = () => {
  return (
    <footer className="bg-dark text-white py-4 mt-auto">
      <div className="container">
        <div className="row align-items-center">
          <div className="col-md-6 text-center text-md-start mb-2 mb-md-0">
            <p className="mb-0">
              &copy; {new Date().getFullYear()} TileVerse. All rights reserved.
            </p>
          </div>

          <div className="col-md-6 text-center text-md-end">
            <Link to="/privacy" className="text-white me-3 text-decoration-none">
              Privacy Policy
            </Link>
            <Link to="/terms" className="text-white text-decoration-none">
              Terms of Service
            </Link>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
