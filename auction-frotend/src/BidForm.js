import React, { useState, useEffect } from 'react';
import axios from 'axios';

function BidForm({ endTime }) {
  const [itemName, setItemName] = useState('');
  const [bidderName, setBidderName] = useState('');
  const [bidAmount, setBidAmount] = useState('');
  const [message, setMessage] = useState('');
  const [isTimeUp, setIsTimeUp] = useState(false);
  const [maxBid, setMaxBid] = useState(0);

  // Check if time is up
  useEffect(() => {
    if (!endTime) return;
    const interval = setInterval(() => {
      const now = new Date();
      if (now >= new Date(endTime)) {
        setIsTimeUp(true);
        clearInterval(interval);
      }
    }, 1000);
    return () => clearInterval(interval);
  }, [endTime]);

  // Fetch max bid dynamically when itemName changes
  useEffect(() => {
    if (!itemName || isTimeUp) return;

    const fetchMaxBid = async () => {
      try {
        const res = await axios.get(`http://localhost:8080/results/${itemName}`);
        setMaxBid(res.data);
      } catch (err) {
        console.error("Failed to fetch max bid", err);
        setMaxBid(0); // fallback
      }
    };

    fetchMaxBid();
    const interval = setInterval(fetchMaxBid, 3000);
    return () => clearInterval(interval);
  }, [itemName, isTimeUp]);

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    const bid = {
      bidderName,
      productName: itemName,
      bidAmount: parseFloat(bidAmount)
    };

    try {
      await axios.post('http://localhost:8080/bids', bid);
      setMessage('✅ Bid placed!');
      setBidderName('');
      setBidAmount('');
    } catch (err) {
      setMessage('❌ Failed to place bid');
    }
  };

  return (
    <div>
      <h2>Real-Time Bidding</h2>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Item Name"
          value={itemName}
          onChange={(e) => setItemName(e.target.value)}
          required
        />

        <input
          type="text"
          placeholder="Your Name"
          value={bidderName}
          onChange={(e) => setBidderName(e.target.value)}
          required
        />

        <input
          type="number"
          placeholder="Your Bid"
          value={bidAmount}
          onChange={(e) => setBidAmount(e.target.value)}
          required
        />

        <button type="submit" disabled={isTimeUp || !itemName}>
          Submit Bid
        </button>
      </form>

      <p>🔥 Current Highest Bid for <b>{itemName || '...'}</b>: ₹{maxBid}</p>

      {isTimeUp && <p>⏱️ Bidding time is over!</p>}
      {message && <p>{message}</p>}
    </div>
  );
}

export default BidForm;
