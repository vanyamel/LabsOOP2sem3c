import { render, screen } from '@testing-library/react';
import App from './App';

test('renders Patients header', () => {
  render(<App />);
  const headerElement = screen.getByText(/Patients/i);
  expect(headerElement).toBeInTheDocument();
});
