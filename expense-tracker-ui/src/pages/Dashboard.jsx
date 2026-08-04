import Sidebar from "../components/Sidebar";
import DashboardCard from "../components/DashboardCard";
import "../styles/dashboard.css";

function Dashboard() {

    const cards = [
        {
            id: 1,
            title: "Monthly Budget",
            value: "₹15000"
        },
        {
            id: 2,
            title: "Spent",
            value: "₹6200"
        },
        {
            id: 3,
            title: "Remaining",
            value: "₹8800"
        },
        {
            id: 4,
            title: "Savings",
            value: "₹5000"
        }
    ];

    const transactions = [
        {
            id: 1,
            title: "Netflix",
            amount: "₹649"
        },
        {
            id: 2,
            title: "Dominos",
            amount: "₹699"
        },
        {
            id: 3,
            title: "Electricity Bill",
            amount: "₹3000"
        },
        {
            id: 4,
            title: "Spotify",
            amount: "₹119"
        },
        {
             id: 5,
             title: "Fruits",
             amount: "₹299"
        }
    ];

    return (

        <div className="dashboard">

            <Sidebar />

            <main className="content">

                <div className="cards">

                    {cards.map(card => (

                        <DashboardCard
                            key={card.id}
                            title={card.title}
                            value={card.value}
                        />

                    ))}

                </div>

                <section className="transactions">

                    <h2>Recent Transactions</h2>

                    <div className="transaction-list">

                        {transactions.map((transaction) => (

                            <div
                                key={transaction.id}
                                className="transaction-item"
                            >

                                <span>{transaction.title}</span>

                                <span>{transaction.amount}</span>

                            </div>

                        ))}

                    </div>

                </section>

            </main>

        </div>

    );

}

export default Dashboard;