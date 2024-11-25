import React, { useState } from 'react';

const ListingPage = () => {
  const [formData, setFormData] = useState({
    title: '',
    description: '',
    price: '',
    make: '',
    model: '',
    year: '',
    seatingCapacity: '',
    condition: '',
    images: [],
    sellerId: 'testSellerId', // Replace with dynamic seller ID if available
    category: '',
    available: true
  });

  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSuccessMessage('');
    setErrorMessage('');

    try {
      const response = await fetch('http://localhost:8080/api/posts/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
      });

      if (!response.ok) {
        throw new Error('Failed to create post');
      }

      setSuccessMessage('Post created successfully!');
      setFormData({
        title: '',
        description: '',
        price: '',
        make: '',
        model: '',
        year: '',
        seatingCapacity: '',
        condition: '',
        images: [],
        sellerId: 'testSellerId', // Reset seller ID dynamically if needed
        category: '',
        available: true
      });
    } catch (error) {
      setErrorMessage(error.message);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-gray-50 to-gray-100 flex justify-center items-center">
      <div className="w-full max-w-4xl bg-white rounded-xl shadow-lg p-6">
        <h1 className="text-3xl font-bold mb-6 text-center">Create a New Listing</h1>

        {successMessage && <div className="text-green-600 mb-4">{successMessage}</div>}
        {errorMessage && <div className="text-red-600 mb-4">{errorMessage}</div>}

        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <input
              type="text"
              name="title"
              placeholder="Title"
              value={formData.title}
              onChange={handleInputChange}
              className="w-full p-3 border rounded-lg"
              required
            />
            <input
              type="number"
              name="price"
              placeholder="Price"
              value={formData.price}
              onChange={handleInputChange}
              className="w-full p-3 border rounded-lg"
              required
            />
            <input
              type="text"
              name="make"
              placeholder="Make"
              value={formData.make}
              onChange={handleInputChange}
              className="w-full p-3 border rounded-lg"
            />
            <input
              type="text"
              name="model"
              placeholder="Model"
              value={formData.model}
              onChange={handleInputChange}
              className="w-full p-3 border rounded-lg"
            />
            <input
              type="number"
              name="year"
              placeholder="Year"
              value={formData.year}
              onChange={handleInputChange}
              className="w-full p-3 border rounded-lg"
            />
            <input
              type="number"
              name="seatingCapacity"
              placeholder="Seating Capacity"
              value={formData.seatingCapacity}
              onChange={handleInputChange}
              className="w-full p-3 border rounded-lg"
            />
          </div>

          <textarea
            name="description"
            placeholder="Description"
            value={formData.description}
            onChange={handleInputChange}
            className="w-full p-3 border rounded-lg"
            rows="4"
            required
          />

          <div>
            <label htmlFor="condition" className="block text-sm font-medium text-gray-700">
              Condition
            </label>
            <select
              name="condition"
              value={formData.condition}
              onChange={handleInputChange}
              className="w-full p-3 border rounded-lg"
              required
            >
              <option value="">Select Condition</option>
              <option value="new">New</option>
              <option value="used">Used</option>
              <option value="certified">Certified Pre-Owned</option>
            </select>
          </div>

          <div>
            <label htmlFor="category" className="block text-sm font-medium text-gray-700">
              Category
            </label>
            <input
              type="text"
              name="category"
              placeholder="Category"
              value={formData.category}
              onChange={handleInputChange}
              className="w-full p-3 border rounded-lg"
              required
            />
          </div>

          <div>
            <label htmlFor="images" className="block text-sm font-medium text-gray-700">
              Image URLs (Comma Separated)
            </label>
            <input
              type="text"
              name="images"
              placeholder="Enter image URLs"
              value={formData.images.join(', ')}
              onChange={(e) =>
                setFormData({ ...formData, images: e.target.value.split(',').map((url) => url.trim()) })
              }
              className="w-full p-3 border rounded-lg"
            />
          </div>

          <button
            type="submit"
            className="w-full py-3 bg-blue-600 text-white font-bold rounded-lg hover:bg-blue-700 transition"
          >
            Create Listing
          </button>
        </form>
      </div>
    </div>
  );
};

export default ListingPage;
