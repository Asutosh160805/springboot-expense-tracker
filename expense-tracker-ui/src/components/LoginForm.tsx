import {useContext, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {handleError, handleSuccess} from "../utils/utility.ts";
import {ToastContainer} from "react-toastify";
import {authenticateUser} from "../services/auth.ts";
import {LocalAuthContext} from "./AuthProvider.tsx";

export function LoginForm() {
    const [formData, setFormData] = useState({
        email : "",
        password : ""
    });

    const [loading, setLoading] = useState(false);
    //const {setAccessToken} = useContext(TokenContext);
    const {login} = useContext(LocalAuthContext)

    const navigate = useNavigate();

    async function handleSubmit(e : any) {
        e.preventDefault();

        setLoading(true);

        try {

            if (!formData.email || !formData.password) {
                throw new Error('Email and Password are required');
            }

            const data = await authenticateUser(formData);

            //setAccessToken(data.token);
            login(data.token);

            handleSuccess("Logged in successfully");

            setTimeout(() => {
                navigate('/home', {replace : true})
            }, 1000);

        } catch(error : any) {
            handleError(error.message);
        } finally {
            setLoading(false);
        }
    }

    function handleChange(e : any) {
        const {name, value} = e.target;
        setFormData(prev => ({
            ...prev,
            [name] : value
        }));
    }

    return (
        <div className='container'>
            <h1>Login</h1>
            <form onSubmit={handleSubmit}>

                <div>
                    <label htmlFor='email'>Email</label>
                    <input
                        type='email'
                        name='email'
                        placeholder='Enter your email'
                        value={formData.email}
                        onChange={handleChange}
                    />
                </div>

                <div>
                    <label htmlFor='password'>Password</label>
                    <input
                        type='password'
                        name='password'
                        placeholder='Password'
                        value={formData.password}
                        onChange={handleChange}
                    />
                </div>

                <button type="submit" disabled={loading}>
                    {loading ? "Logging in..." : "Login"}
                </button>
                <br/>
                <span>
                    Do not have an account?
                    <Link to='/register'>Create account</Link>
                </span>
            </form>
            <ToastContainer />
        </div>
    );
}

export default LoginForm;