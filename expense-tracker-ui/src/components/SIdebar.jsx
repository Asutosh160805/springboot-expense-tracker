import "../styles/sidebar.css";

import {
    FaHome,
    FaWallet,
    FaChartPie,
    FaChartBar,
    FaUser,
    FaSignOutAlt
} from "react-icons/fa";

function Sidebar() {
    return (
        <aside className="sidebar">

            <ul>

                <li><FaHome /> Dashboard</li>
                <li><FaWallet /> Expenses</li>
                <li><FaChartPie /> Budgets</li>
                <li><FaChartBar /> Reports</li>
                <li><FaUser /> Profile</li>
                <li><FaSignOutAlt /> Logout</li>

            </ul>

        </aside>
    );
}

export default Sidebar;