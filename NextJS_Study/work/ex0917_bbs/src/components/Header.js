import Nav from "./Nav";

export default function Header() {
    return(
        <div style={{width: '80%', margin: 'auto', padding: '20px', textAlign: 'center'}}>
            <div style={{textAlign: 'left'}}>
                <img src="/images/logo.png" alt="logo"/>
            </div>
            <Nav/>
        </div>
    );
};