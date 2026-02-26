import React from 'react';
import { render, screen } from '@testing-library/react';
import DynamicList from './DynamicList';

test('renders dynamic list columns and data', () => {
  const view = {
    columns: [
      { name: 'name', label: 'Name' },
      { name: 'email', label: 'Email' },
    ],
  };

  const data = [
    { name: 'John Doe', email: 'john@example.com' },
    { name: 'Jane Doe', email: 'jane@example.com' },
  ];

  render(<DynamicList view={view} data={data} />);

  expect(screen.getByText('Name')).toBeInTheDocument();
  expect(screen.getByText('Email')).toBeInTheDocument();
  expect(screen.getByText('John Doe')).toBeInTheDocument();
  expect(screen.getByText('john@example.com')).toBeInTheDocument();
  expect(screen.getByText('Jane Doe')).toBeInTheDocument();
  expect(screen.getByText('jane@example.com')).toBeInTheDocument();
});
