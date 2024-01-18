export interface AuthContext {
  authToken: string | undefined;
  setAuthToken: (token: string) => void;
}
