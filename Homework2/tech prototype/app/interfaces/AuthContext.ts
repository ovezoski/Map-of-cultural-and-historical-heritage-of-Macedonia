interface AuthContext {
    authToken: string;
    setAuthToken: (token: string) => void;
}