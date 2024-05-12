import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import KeycloakProvider from './auth/KeycloakProvider';

ReactDOM.render(
    <React.StrictMode>
        <KeycloakProvider>
            <App />
        </KeycloakProvider>
    </React.StrictMode>,
    document.getElementById('root')
);
