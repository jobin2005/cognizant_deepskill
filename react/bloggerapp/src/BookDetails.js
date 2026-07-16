import React from 'react';

const books = [
  { id: 1, title: 'React Patterns', author: 'Michael Chan' },
  { id: 2, title: 'Learning React', author: 'Alex Banks' },
  { id: 3, title: 'Pure React', author: 'Dave Ceddia' },
];

function BookDetails() {
  return (
    <div className="details-card">
      <h2>Book Details</h2>
      <ul>
        {books.map(book => (
          <li key={book.id}>
            <strong>{book.title}</strong> by {book.author}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default BookDetails;
