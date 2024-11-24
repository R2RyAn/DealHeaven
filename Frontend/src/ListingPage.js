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
    images: []
  });

  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleFileChange = (e) => {
    setFormData({ ...formData, images: e.target.files });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSuccessMessage('');
    setErrorMessage('');

    const formDataToSend = new FormData();
    for (const key in formData) {
      if (key === 'images') {
        Array.from(formData.images).forEach((file) => {
          formDataToSend.append('images', file);
        });
      } else {
        formDataToSend.append(key, formData[key]);
      }
    }

    try {
      const response = await fetch('http://localhost:8080/api/posts/create', {
        method: 'POST',
        body: formDataToSend
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
        images: []
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
            <label htmlFor="images" className="block text-sm font-medium text-gray-700">
              Upload Images
            </label>
            <input
              type="file"
              name="images"
              multiple
              onChange={handleFileChange}
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
