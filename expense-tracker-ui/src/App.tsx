import './App.css'
import {Login} from "./pages/Login.tsx";
import { Route, Routes} from 'react-router-dom';
import {SignUp} from "./pages/SignUp.tsx";
import {Dashboard} from "./pages/Dashboard.tsx";
import {Landing} from "./pages/Landing.tsx";
import {Budget} from "./pages/Budget.tsx";
import {Expenses} from "./pages/Expenses.tsx";
import {ProtectedRoute} from "./components/ProtectedRoute.tsx";
import {Logout} from "./pages/Logout.tsx";

function App() {
  return (
      <div className='App'>
              <Routes>
                  <Route path='/' element={<Landing/>}/>
                  <Route path='/login' element={<Login/>}/>
                  <Route path='/register' element={<SignUp/>}/>
                  <Route path='/logout' element={<Logout/>}/>
                  <Route element={<ProtectedRoute />}>
                      <Route path={'/dashboard'} element={<Dashboard/>}/>
                      <Route path='/budget' element={<Budget/>}/>
                      <Route path='/expenses' element={<Expenses/>}/>
                  </Route>
              </Routes>
      </div>
  )
}

export default App
