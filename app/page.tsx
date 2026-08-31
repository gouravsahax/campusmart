export default function Home() {
  return (
    <main className="hero-container">
      <h1 className="title">CampusMart</h1>
      <p className="subtitle">
        The most exclusive marketplace for your campus community. Buy, sell, and trade with students you trust.
      </p>
      
      <div className="button-group">
        <a href="/login" className="btn btn-secondary">
          Log In
        </a>
        <a href="/signup" className="btn btn-primary">
          Sign Up
        </a>
      </div>
    </main>
  );
}
