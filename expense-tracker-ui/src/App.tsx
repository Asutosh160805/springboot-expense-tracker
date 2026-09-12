import './App.css'
import {Login} from "./pages/Login.tsx";
import { Navigate, Route, Routes } from 'react-router-dom';
import {SignUp} from "./pages/SignUp.tsx";
import {Home} from "./pages/Home.tsx";

function App() {
  return (
      <div className='App'>
          <Routes>
              <Route path='/' element={<Navigate to='/home' />}/>
              <Route path='/login' element={<Login />}/>
              <Route path='/register' element={<SignUp />}/>
              <Route path='/home' element={<Home />}/>
          </Routes>
      </div>
  )
}

export default App
