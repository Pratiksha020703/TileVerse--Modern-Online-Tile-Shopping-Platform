// // import { useState } from "react";
// // import "../styles/Gallery.css";

// // const tiles = [
// //   {
// //     id: 1,
// //     name: "Carrara Marble Tile",
// //     color: "White",
// //     size: "24x24",
// //     style: "Marble",
// //     price: 12.99,
// //     image: "https://via.placeholder.com/400x400?text=Marble",
// //   },
// //   {
// //     id: 2,
// //     name: "Matte Gray Porcelain",
// //     color: "Gray",
// //     size: "12x24",
// //     style: "Porcelain",
// //     price: 4.5,
// //     image: "https://via.placeholder.com/400x400?text=Porcelain",
// //   },
// //   {
// //     id: 3,
// //     name: "Wood Look Ceramic",
// //     color: "Brown",
// //     size: "8x48",
// //     style: "Wood Look",
// //     price: 3.99,
// //     image: "https://via.placeholder.com/400x400?text=Wood+Look",
// //   },
// // ];

// // function Gallery() {
// //   const [color, setColor] = useState("All");
// //   const [size, setSize] = useState("All");
// //   const [style, setStyle] = useState("All");

// //   const filteredTiles = tiles.filter(tile =>
// //     (color === "All" || tile.color === color) &&
// //     (size === "All" || tile.size === size) &&
// //     (style === "All" || tile.style === style)
// //   );

// //   return (
// //     <>
// //       {/* Hero */}
// //       <section className="tile-hero">
// //         <div>
// //           <h1>Premium Tiles</h1>
// //           <p className="text-coral">Color • Size • Style</p>
// //         </div>
// //       </section>

// //       <div className="container">
// //         {/* Filters */}
// //         <div className="filters">
// //           <select value={color} onChange={e => setColor(e.target.value)}>
// //             <option>All</option>
// //             <option>White</option>
// //             <option>Gray</option>
// //             <option>Brown</option>
// //           </select>

// //           <select value={size} onChange={e => setSize(e.target.value)}>
// //             <option>All</option>
// //             <option>12x24</option>
// //             <option>24x24</option>
// //             <option>8x48</option>
// //           </select>

// //           <select value={style} onChange={e => setStyle(e.target.value)}>
// //             <option>All</option>
// //             <option>Marble</option>
// //             <option>Porcelain</option>
// //             <option>Wood Look</option>
// //           </select>
// //         </div>

// //         {/* Grid */}
// //         <div className="tile-grid">
// //           {filteredTiles.map(tile => (
// //             <div className="tile-card" key={tile.id}>
// //               <img src={tile.image} alt={tile.name} />
// //               <div className="tile-info">
// //                 <h3>{tile.name}</h3>
// //                 <p>
// //                   {tile.color} • {tile.size} • {tile.style}
// //                 </p>
// //                 <strong>${tile.price} / sq ft</strong>
// //                 <button className="btn-coral-outline">
// //                   View Details
// //                 </button>
// //               </div>
// //             </div>
// //           ))}
// //         </div>

// //         {filteredTiles.length === 0 && (
// //           <p className="empty">No tiles found.</p>
// //         )}
// //       </div>
// //     </>
// //   );
// // }

// // export default Gallery;

// import { useEffect, useState } from "react";
// import publicApi from "../api/publicApi";
// import "../styles/Gallery.css";

// function Gallery() {
//   const [products, setProducts] = useState([]);
//   const [categories, setCategories] = useState([]);

//   const [selectedCategory, setSelectedCategory] = useState("All");

//   useEffect(() => {
//     publicApi.get("/api/products").then(res => {
//       setProducts(res.data);

//       const uniqueCategories = [
//         ...new Set(res.data.map(p => p.category?.categoryName))
//       ];
//       setCategories(uniqueCategories);
//     });
//   }, []);

//   const filteredTiles = products.filter(p =>
//     selectedCategory === "All" || p.category?.categoryName === selectedCategory
//   );

//   return (
//     <>
//       {/* HERO */}
//       <section className="tile-hero">
//         <div>
//           <h1>Premium Tiles</h1>
//           <p className="text-coral">Color • Size • Style</p>
//         </div>
//       </section>

//       <div className="container">

//         {/* FILTER BAR */}
//         <div className="filters">
//           <select
//             value={selectedCategory}
//             onChange={e => setSelectedCategory(e.target.value)}
//           >
//             <option value="All">All Categories</option>
//             {categories.map((c, i) => (
//               <option key={i} value={c}>{c}</option>
//             ))}
//           </select>
//         </div>

//         {/* GRID */}
//         <div className="tile-grid">
//           {filteredTiles.map(tile => (
//             <div className="tile-card" key={tile.productId}>
//               <img src={tile.imageUrl} alt={tile.productName} />

//               <div className="tile-info">
//                 <h3>{tile.productName}</h3>

//                 <p>
//                   {tile.material} • {tile.size} • {tile.category?.categoryName}
//                 </p>

//                 <strong>₹ {tile.pricePerBox} / box</strong>

//                 <button className="btn-coral-outline">
//                   View Details
//                 </button>
//               </div>
//             </div>
//           ))}
//         </div>

//         {filteredTiles.length === 0 && (
//           <p className="empty">No tiles found.</p>
//         )}
//       </div>
//     </>
//   );
// }

// export default Gallery;



import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import publicApi from "../api/publicApi";
import "../styles/Gallery.css";
import { useLocation } from "react-router-dom";


function Gallery() {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("All");
  const [loading, setLoading] = useState(true);

  const location = useLocation();


  // 🔹 Fetch products from MySQL via backend
  useEffect(() => {
    publicApi
      .get("/api/products")
      .then((res) => {
        const data = res.data;
        setProducts(data);

        // Extract unique categories
        const uniqueCategories = [
          ...new Set(
            data
              // .map((p) => p.category?.categoryName)
              .map((p) => p.categoryName).filter(Boolean)

              .filter(Boolean)
          ),
        ];

        setCategories(uniqueCategories);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Failed to fetch products:", err);
        setLoading(false);
      });
  }, []);

  useEffect(() => {
  const params = new URLSearchParams(location.search);
  const categoryFromUrl = params.get("category");

  if (categoryFromUrl && categories.length > 0) {
    const matchedCategory = categories.find(cat =>
      cat.toLowerCase().includes(categoryFromUrl.toLowerCase())
    );

    if (matchedCategory) {
      setSelectedCategory(matchedCategory);
    }
  }
}, [location.search, categories]);



  // 🔹 Filter by category
  const filteredTiles =
    selectedCategory === "All"
      ? products
      : products.filter(
          //(p) => p.category?.categoryName === selectedCategory
          (p) => p.categoryName === selectedCategory
        );

  if (loading) {
    return <p className="loading">Loading tiles...</p>;
  }

  return (
    <>
      {/* HERO */}
      <section className="tile-hero">
        <div>
          <h1>Premium Tiles</h1>
          <p className="text-coral">Color • Size • Style</p>
        </div>
      </section>

      <div className="container">
        {/* FILTER BAR */}
        <div className="filters">
          <select
            value={selectedCategory}
            onChange={(e) => setSelectedCategory(e.target.value)}
          >
            <option value="All">All Categories</option>
            {categories.map((category, index) => (
              <option key={index} value={category}>
                {category}
              </option>
            ))}
          </select>
        </div>

        {/* GRID */}
        <div className="tile-grid">
          {filteredTiles.map((tile) => (
            <div className="tile-card" key={tile.productId}>
              <img
                src={tile.imageUrl}
                alt={tile.productName}
                onError={(e) => {
                  e.target.src = "/images/no-image.png";
                }}
              />

              <div className="tile-info">
                <h3>{tile.productName}</h3>

                <p>
                  {tile.material} • {tile.size} •{" "}
                  {/* {tile.category?.categoryName} */}
                  {tile.categoryName}

                </p>

                <strong>₹ {tile.pricePerBox} / box</strong>

                {/* ✅ Correct navigation */}
                <Link to={`/productdetails/${tile.productId}`}>
                  <button className="btn-coral-outline">
                    View Details
                  </button>
                </Link>
              </div>
            </div>
          ))}
        </div>

        {filteredTiles.length === 0 && (
          <p className="empty">No tiles found.</p>
        )}
      </div>
    </>
  );
}

export default Gallery;
