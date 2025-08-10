import React, {useEffect, useState} from 'react';
import axios from 'axios';

export default function Users(){
  const [users,setUsers]=useState([]);
  const [form,setForm]=useState({name:'',email:''});
  useEffect(()=>{ fetch(); },[]);
  function fetch(){ axios.get('/api/users').then(r=>setUsers(r.data)); }
  function save(e){
    e.preventDefault();
    axios.post('/api/users', form).then(()=>{ setForm({name:'',email:''}); fetch(); });
  }
  function remove(id){ axios.delete('/api/users/'+id).then(()=>fetch()); }
  return (
    <div>
      <h2>Users</h2>
      <form onSubmit={save}>
        <input placeholder="name" value={form.name} onChange={e=>setForm({...form,name:e.target.value})} required/>
        <input placeholder="email" value={form.email} onChange={e=>setForm({...form,email:e.target.value})} required/>
        <button type="submit">Add</button>
      </form>
      <ul>
        {users.map(u=>(
          <li key={u.id}>{u.name} ({u.email}) <button onClick={()=>remove(u.id)}>Delete</button></li>
        ))}
      </ul>
    </div>
  );
}
