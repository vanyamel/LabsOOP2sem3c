import React, { useEffect, useState } from 'react';
import keycloak from './keycloak-config';

const KeycloakProvider = ({ children }) => {
    const [keycloakInitialized, setKeycloakInitialized] = useState(false);

    useEffect(() => {
        keycloak.init({ onLoad: 'login-required' }).then((authenticated) => {
            setKeycloakInitialized(true);
            if (authenticated) {
                console.log('User is authenticated');
            } else {
                console.log('User is not authenticated');
            }
        });

        return () => {
            keycloak.logout();
        };
    }, []);

    if (!keycloakInitialized) {
        return <div>Loading...</div>;
    }

    return <>{children}</>;
};

export default KeycloakProvider;
