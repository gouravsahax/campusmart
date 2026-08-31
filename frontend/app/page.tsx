import Link from 'next/link';

export default function Home() {
  return (
    <main className="overflow-hidden">
      <section className="relative isolate flex flex-col justify-center items-center h-screen pb-6" aria-labelledby="hero-title">
        
        {/* Background Image with lighter, fresh overlay */}
        <div 
          className="absolute inset-0 -z-10 bg-[url('/campus.jpg')] bg-cover bg-bottom before:absolute before:inset-0 before:bg-gradient-to-b before:from-[#eef5f9]/90 before:via-[#eef5f9]/60 before:to-transparent" 
          aria-hidden="true" 
        />
        
        {/* Header matching the image */}
        <header className="absolute top-0 left-0 right-0 flex justify-between items-center px-10 py-6 z-10 max-md:px-5">
          <Link href="/" className="flex items-center gap-2.5" aria-label="CampusMart home">
            <img src="/logo.png" alt="CampusMart Logo" className="w-10 h-10 object-contain" />
            <span className="text-[1.6rem] font-extrabold tracking-[-0.03em] text-[#1a202c] leading-none">
              CampusMart
            </span>
          </Link>
          
          <div className="flex justify-end">
            <Link href="/login" className="flex items-center justify-center h-10 px-6 text-[15px] font-bold rounded-sm bg-[#111827] text-white hover:bg-black transition-all">
              Get Started
            </Link>
          </div>
        </header>

        {/* Hero Content */}
        <div className="max-w-[900px] text-center px-6 pt-12 pb-16 animate-fade-up flex flex-col items-center">
          
          <span className="inline-flex items-center gap-2 px-5 py-2 bg-white/30 text-[#4a5568] shadow-[0_4px_30px_rgba(0,0,0,0.1)] rounded-sm text-[12px] font-bold uppercase tracking-wider mb-8 border border-white/40 backdrop-blur-md">
            Your Campus Marketplace 
            <img src="/ju.png" alt="Jadavpur University" className="w-5 h-5 rounded-full object-cover" />
            Built by students of Jadavpur University
          </span>
          
          <h1 id="hero-title" className="text-[clamp(2.5rem,5.5vw,4.5rem)] font-bold tracking-tight leading-[1.1] text-[#1a202c] mb-6 max-w-[800px]">
            Buy, sell, and trade with <br className="hidden md:block" />
            <span className="inline-flex items-center gap-2 italic font-serif font-medium text-[#1a202c]">
              fellow students 🎓
            </span>
          </h1>
          
          <p className="text-[17px] text-[#4a5568] max-w-[600px] mx-auto mb-10 leading-relaxed font-medium">
            Fast, safe, and right on campus. Find what you need without the hassle of shipping or dealing with strangers.
          </p>
          
          <div className="flex gap-4 justify-center flex-wrap">
            <Link href="/login" className="flex items-center gap-2 justify-center h-12 px-8 text-[15px] font-bold rounded-sm bg-white text-[#1a202c] shadow-sm hover:shadow-md transition-all cursor-pointer">
              Login
            </Link>
            <Link href="/signup" className="flex items-center gap-2 justify-center h-12 px-8 text-[15px] font-bold rounded-sm bg-[#111827] text-white shadow-sm hover:bg-black hover:shadow-md transition-all cursor-pointer">
              Signup
              <svg width="14" height="14" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M5 12H19M19 12L12 5M19 12L12 19" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"/>
              </svg>
            </Link>
          </div>
        </div>
      </section>
    </main>
  );
}
