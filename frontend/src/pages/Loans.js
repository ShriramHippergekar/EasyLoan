import React, {useEffect, useState} from 'react';
import axios from 'axios';

export default function Loans(){
  const [loans,setLoans]=useState([]);
  const [users,setUsers]=useState([]);
  const [form,setForm]=useState({applicantId:'',amount:1000,termMonths:12,type:'PERSONAL'});
  const [filter,setFilter]=useState({status:'',userId:''});

  useEffect(()=>{
    axios.get('/api/users').then(r=>setUsers(r.data));
    fetchLoans();
  },[]);

  function fetchLoans(){
    let q = '/api/loans';
    const params = [];
    if(filter.status) params.push('status='+filter.status);
    if(filter.userId) params.push('userId='+filter.userId);
    if(params.length) q += '?'+params.join('&');
    axios.get(q).then(r=>setLoans(r.data));
  }

  function apply(e){
    e.preventDefault();
    axios.post('/api/loans', {
      applicant: { id: Number(form.applicantId) },
      amount: Number(form.amount),
      termMonths: Number(form.termMonths),
      type: form.type
    }).then(()=>{ fetchLoans(); });
  }

  function changeStatus(id, act){
    axios.post('/api/loans/'+id+'/'+act).then(()=>fetchLoans());
  }

  return (
    <div>
      <h2>Loans</h2>
      <div style={{border:'1px solid #ddd', padding:10, marginBottom:10}}>
        <h3>Apply</h3>
        <form onSubmit={apply}>
          <select value={form.applicantId} onChange={e=>setForm({...form,applicantId:e.target.value})} required>
            <option value="">Select user</option>
            {users.map(u=> <option key={u.id} value={u.id}>{u.name}</option>)}
          </select>
          <input type="number" value={form.amount} onChange={e=>setForm({...form,amount:e.target.value})} min="1" required/>
          <input type="number" value={form.termMonths} onChange={e=>setForm({...form,termMonths:e.target.value})} min="1" required/>
          <input value={form.type} onChange={e=>setForm({...form,type:e.target.value})} required/>
          <button type="submit">Apply</button>
        </form>
      </div>

      <div style={{marginBottom:10}}>
        <select value={filter.status} onChange={e=>setFilter({...filter,status:e.target.value})}>
          <option value="">All statuses</option>
          <option value="PENDING">PENDING</option>
          <option value="APPROVED">APPROVED</option>
          <option value="REJECTED">REJECTED</option>
        </select>
        <select value={filter.userId} onChange={e=>setFilter({...filter,userId:e.target.value})}>
          <option value="">All users</option>
          {users.map(u=> <option key={u.id} value={u.id}>{u.name}</option>)}
        </select>
        <button onClick={fetchLoans}>Filter</button>
      </div>

      <table border="1" cellPadding="6">
        <thead><tr><th>ID</th><th>Applicant</th><th>Amount</th><th>Term</th><th>Type</th><th>Status</th><th>Actions</th></tr></thead>
        <tbody>
          {loans.map(l=>(
            <tr key={l.id}>
              <td>{l.id}</td>
              <td>{l.applicant?.name}</td>
              <td>{l.amount}</td>
              <td>{l.termMonths}</td>
              <td>{l.type}</td>
              <td>{l.status}</td>
              <td>
                <button onClick={()=>changeStatus(l.id,'approve')}>Approve</button>
                <button onClick={()=>changeStatus(l.id,'reject')}>Reject</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
