import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import DynamicForm from './DynamicForm';
import '@testing-library/jest-dom';

const mockSchema = {
  model: 'user',
  type: 'form',
  title: 'User Form',
  fields: [
    {
      name: 'username',
      label: 'Username',
      type: 'string',
      widget: 'input',
      required: true,
    },
    {
      name: 'email',
      label: 'Email',
      type: 'string',
      widget: 'input',
      required: true,
    },
    {
      name: 'role',
      label: 'Role',
      type: 'selection',
      widget: 'select',
      options: [
        { value: 'ADMIN', label: 'Administrator' },
        { value: 'USER', label: 'User' },
      ],
    },
  ],
};

test('renders dynamic form based on schema', () => {
  const handleSubmit = jest.fn();
  render(<DynamicForm schema={mockSchema} onSubmit={handleSubmit} />);

  // Check if title is rendered
  expect(screen.getByText('User Form')).toBeInTheDocument();

  // Check if inputs are rendered
  expect(screen.getByLabelText(/Username/i)).toBeInTheDocument();
  expect(screen.getByLabelText(/Email/i)).toBeInTheDocument();
  expect(screen.getByLabelText(/Role/i)).toBeInTheDocument();

  // Check if select options are rendered
  const roleSelect = screen.getByLabelText(/Role/i);
  expect(roleSelect).toHaveTextContent('Administrator');
  expect(roleSelect).toHaveTextContent('User');

  // Simulate user input
  fireEvent.change(screen.getByLabelText(/Username/i), { target: { value: 'jdoe' } });
  fireEvent.change(screen.getByLabelText(/Email/i), { target: { value: 'jdoe@example.com' } });
  fireEvent.change(screen.getByLabelText(/Role/i), { target: { value: 'ADMIN' } });

  // Submit form
  fireEvent.click(screen.getByText('Save'));

  // Verify submission
  expect(handleSubmit).toHaveBeenCalledWith({
    username: 'jdoe',
    email: 'jdoe@example.com',
    role: 'ADMIN',
  });
});