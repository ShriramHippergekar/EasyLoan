import React from 'react';
import Users from './pages/Users';
import Loans from './pages/Loans';

export default function App(){
  const [page, setPage] = React.useState('users');
  return (
    <div style={{padding:20,fontFamily:'Arial'}}>
      <h1>EasyLoan</h1>
      <nav style={{marginBottom:10}}>
        <button onClick={()=>setPage('users')}>Users</button>
        <button onClick={()=>setPage('loans')}>Loans</button>
      </nav>
      {page==='users' ? <Users/> : <Loans/>}
    </div>
  );
}
