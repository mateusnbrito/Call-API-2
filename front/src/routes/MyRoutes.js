import  {Routes, Route} from 'react-router-dom'
import SignIn from '../pages/SignIn'
import SignUp from '../pages/SignUp'
import Dashboard from '../pages/Dashboard'
import Profile from '../pages/Profile'
import Costumers from '../pages/Costumers'
import New from '../pages/New';
import ProtectedRoute from './protectedRoute';

export default function MyRoutes(){
  return(
    <Routes>
      <Route path='/' key='/' element={<SignIn/>}/>
      <Route path='/register' key='/register' element={<SignUp/>}/>
      <Route element={<ProtectedRoute/>}>
        <Route path='/dashboard' key='/dashboard' element={<Dashboard/>}/>
        <Route path='/profile' key='/profile' element={<Profile/>}/>
        <Route path='/costumers' key='/costumers' element={<Costumers/>}/>
        <Route path='/new' key='/new' element={<New/>}/>
      </Route>
    </Routes>
  );
}