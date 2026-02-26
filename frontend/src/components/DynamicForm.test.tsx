import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import DynamicForm from './DynamicForm';

test('renders dynamic form fields', () => {
  const view = {
    fields: [
      { name: 'username', label: 'Username', type: 'text' },
      { name: 'password', label: 'Password', type: 'password' },
    ],
  };

  render(<DynamicForm view={view} onSubmit={() => {}} />);

  expect(screen.getByLabelText('Username')).toBeInTheDocument();
  expect(screen.getByLabelText('Password')).toBeInTheDocument();
});

test('submits form data', () => {
  const view = {
    fields: [
      { name: 'username', label: 'Username', type: 'text' },
    ],
  };
  const handleSubmit = jest.fn();

  render(<DynamicForm view={view} onSubmit={handleSubmit} />);

  fireEvent.change(screen.getByLabelText('Username'), { target: { value: 'testuser' } });
  fireEvent.click(screen.getByText('Save'));

  expect(handleSubmit).toHaveBeenCalledWith({ username: 'testuser' });
});
