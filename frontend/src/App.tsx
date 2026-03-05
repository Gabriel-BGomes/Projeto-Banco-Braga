import './App.css'

function App() {
  return (
    <div className="app">
      {/* Navbar */}
      <nav className="navbar">
        <div className="logo">Novo<span>Banco</span></div>
        <div className="nav-links">
          <a href="#" className="active">Início</a>
          <a href="#">Cartões</a>
          <a href="#">Investimentos</a>
          <div className="avatar">MA</div>
        </div>
      </nav>

      {/* Conteúdo */}
      <main className="main">
        <p className="date">QUARTA-FEIRA, 4 DE MARÇO</p>
        <h1>Olá, <span className="green">Maria Aparecida</span> 👋</h1>

        {/* Card de saldo */}
        <div className="balance-card">
          <p className="balance-label">SALDO DISPONÍVEL</p>
          <div className="balance-value">
            <span className="currency">R$</span>
            <span className="amount">14.820</span>
            <span className="cents">,57</span>
          </div>
          <div className="badge">↑ +R$ 1.240,00 este mês</div>
          <div className="account-info">
            <div><small>AGÊNCIA</small><p>0042</p></div>
            <div><small>CONTA</small><p>••••-7831</p></div>
            <div><small>TIPO</small><p>Corrente</p></div>
          </div>
        </div>

        {/* Cards de ação */}
        <div className="actions">
          <div className="action-card">
            <div className="icon icon-green">💰</div>
            <h3>Depositar</h3>
            <p>Adicione dinheiro à sua conta de forma rápida e segura.</p>
            <button className="btn btn-green">Depositar agora →</button>
          </div>
          <div className="action-card">
            <div className="icon icon-red">📊</div>
            <h3>Sacar</h3>
            <p>Retire dinheiro disponível a qualquer hora do dia.</p>
            <button className="btn btn-red">Sacar agora →</button>
          </div>
          <div className="action-card">
            <div className="icon icon-blue">↗</div>
            <h3>Transferir</h3>
            <p>Envie dinheiro para qualquer banco via PIX ou TED.</p>
            <button className="btn btn-blue">Transferir agora →</button>
          </div>
        </div>
      </main>
    </div>
  )
}

export default App