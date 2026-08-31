"use client";

import Link from 'next/link';
import { SignIn } from "@/lib/google-auth-action";

export default function LoginPage() {
  return (
    <main className="h-screen overflow-hidden flex flex-col relative isolate">
      {/* Background image with liquid glass overlay gradient exactly like hero page */}
      <div 
        className="absolute inset-0 -z-10 bg-[url('/login.jpg')] bg-cover bg-bottom before:absolute before:inset-0 before:bg-gradient-to-b before:from-[#eef5f9]/90 before:via-[#eef5f9]/60 before:to-transparent" 
        aria-hidden="true" 
      />
      
      {/* Header */}
      <header className="absolute top-0 left-0 right-0 flex justify-between items-center px-10 py-6 z-10 max-md:px-5">
        <Link href="/" className="flex items-center gap-2.5">
          <img src="/logo.png" alt="CampusMart Logo" className="w-8 h-8 object-contain" />
          <span className="text-[1.4rem] font-extrabold tracking-[-0.03em] text-[#1a202c] leading-none">
            CampusMart
          </span>
        </Link>
      </header>

      {/* Main Content */}
      <div className="flex-1 flex items-center justify-center p-6">
        <div className="bg-white/80 backdrop-blur-md border border-white/60 rounded-sm p-8 w-full max-w-[420px] shadow-[0_8px_30px_rgba(0,0,0,0.04)] animate-fade-up">
          <div className="text-center mb-6">
            <h1 className="text-[1.75rem] font-bold tracking-tight text-[#1a202c]">Welcome Back</h1>
          </div>
          
          <div className="space-y-4">
            <div className="space-y-2">
              <label className="block text-[13px] font-bold text-[#1a202c]" htmlFor="email">
                Email
              </label>
              <input 
                type="email" 
                id="email" 
                className="w-full h-11 px-4 bg-white border border-gray-200 rounded-sm text-[15px] font-medium text-[#1a202c] shadow-sm transition-all focus:outline-none focus:border-[#1a202c] focus:ring-1 focus:ring-[#1a202c]" 
                placeholder="you@example.com" 
              />
            </div>
            
            <div className="space-y-2">
              <div className="flex justify-between items-center">
                <label className="block text-[13px] font-bold text-[#1a202c]" htmlFor="password">
                  Password
                </label>
                <Link href="#" className="text-[12px] font-semibold text-[#4a5568] hover:text-[#1a202c]">
                  Forgot password?
                </Link>
              </div>
              <input 
                type="password" 
                id="password" 
                className="w-full h-11 px-4 bg-white border border-gray-200 rounded-sm text-[15px] font-medium text-[#1a202c] shadow-sm transition-all focus:outline-none focus:border-[#1a202c] focus:ring-1 focus:ring-[#1a202c]" 
                placeholder="••••••••" 
              />
            </div>
            
            <button 
              type="button" 
              className="w-full flex items-center justify-center h-12 mt-6 px-4 text-[15px] font-bold rounded-sm bg-[#111827] text-white shadow-sm hover:bg-black hover:shadow-md transition-all cursor-pointer"
            >
              Log In
            </button>

            <div className="relative flex items-center py-2">
              <div className="flex-grow border-t border-gray-200"></div>
              <span className="flex-shrink-0 mx-4 text-[#4a5568] text-[13px] font-medium">or</span>
              <div className="flex-grow border-t border-gray-200"></div>
            </div>

            <button 
              onClick={() => SignIn()}
              className="w-full flex items-center justify-center gap-2 h-12 px-4 text-[15px] font-bold rounded-sm bg-white text-[#1a202c] border border-gray-200 shadow-sm hover:bg-gray-50 hover:shadow-md transition-all cursor-pointer"
            >
              <svg viewBox="0 0 24 24" width="20" height="20" xmlns="http://www.w3.org/2000/svg">
                <path d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" fill="#4285F4"/>
                <path d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" fill="#34A853"/>
                <path d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z" fill="#FBBC05"/>
                <path d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z" fill="#EA4335"/>
              </svg>
              Continue with Google
            </button>
          </div>
          
          <div className="mt-8 text-center text-[14px] font-medium text-[#4a5568]">
            Don't have an account?{' '}
            <Link href="/signup" className="text-[#1a202c] font-bold hover:underline">
              Sign up
            </Link>
          </div>
        </div>
      </div>
    </main>
  );
}
