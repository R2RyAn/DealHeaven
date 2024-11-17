import React, { useState } from 'react';
import { Search } from 'lucide-react';

const HomePage = () => {
  const [searchQuery, setSearchQuery] = useState("");
  const [isFocused, setIsFocused] = useState(false);

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-gray-50 to-gray-100 flex flex-col items-center pt-20">
      {/* Marketplace Title */}
      <h1 className="text-5xl font-bold mb-12 bg-clip-text text-transparent bg-gradient-to-r from-blue-600 to-cyan-600">
        Marketplace
      </h1>

      {/* Search Container */}
      <div className="w-full max-w-2xl px-4">
        <div 
          className={`
            relative flex items-center w-full bg-white rounded-2xl 
            shadow-lg transition-all duration-300 ease-in-out
            ${isFocused ? 'shadow-xl scale-105' : 'hover:shadow-xl hover:scale-105'}
          `}
        >
          {/* Search Icon */}
          <div className="pl-6">
            <Search 
              size={24} 
              className={`
                transition-colors duration-300
                ${isFocused ? 'text-blue-600' : 'text-gray-400'}
              `}
            />
          </div>

          {/* Search Input */}
          <input
            type="text"
            placeholder="Search for anything..."
            value={searchQuery}
            onChange={handleSearchChange}
            onFocus={() => setIsFocused(true)}
            onBlur={() => setIsFocused(false)}
            className="
              w-full py-6 px-4 text-lg text-gray-700 
              placeholder-gray-400 bg-transparent border-none 
              focus:outline-none focus:ring-0
            "
          />
        </div>

        {/* Popular Categories */}
        <div className="mt-8 flex flex-wrap justify-center gap-3">
          {['Mileage', 'Cost', 'Sear Capacity'].map((category) => (
            <button
              key={category}
              className="
                px-4 py-2 bg-white rounded-full text-sm
                text-gray-600 shadow-md hover:shadow-lg
                transition-all duration-300 hover:scale-105
                hover:bg-blue-50 hover:text-blue-600
              "
            >
              {category}
            </button>
          ))}
        </div>

        {/* Search Results Preview */}
        {searchQuery && (
          <div className="mt-6 bg-white rounded-xl shadow-lg p-4 transition-all duration-300">
            <p className="text-gray-600">
              Searching for "{searchQuery}"...
            </p>
          </div>
        )}
      </div>
    </div>
  );
};

export default HomePage;