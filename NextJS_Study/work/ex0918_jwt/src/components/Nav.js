"use client"
import { Box, Grid } from "@mui/material";
import Link from "next/link";

function Nav(accessToken){
    let leftItem = [
        {title:"Home", path:"/Home"}, 
        {title:"Members", path:"/Members"}, 
        {title:"Board", path:"/Board"}, 
    ]

    let rightItem = [
        {title:"Login", path:"/Login"}, 
        {title:"Signup", path:"/Signup"}
    ]

    return(
        <nav id="header" style={{position: "relative"}}>
            <div style={{display: "flex", position: "absolute", left: "10px"}}>
                <Link href="/">
                    <div>Home</div>
                </Link>
                <Link href="/members">
                    <div style={{paddingLeft: "10px"}}>Members</div>
                </Link>
                <Link href="/board">
                    <div style={{paddingLeft: "10px"}}>Board</div>
                </Link>
            </div>

            <div style={{display: "flex", position: "absolute", right: "10px"}}>
                {
                    accessToken == null
                    ? <Link href="/members/login">
                        <div style={{paddingLeft: "10px"}}>Login</div>
                    </Link>

                    : <Link href="/members/login">
                        <div style={{paddingLeft: "10px"}}>Logout</div>
                    </Link>

                }
                <Link href="/members/signup">
                    <div style={{paddingLeft: "10px"}}>Signup</div>
                </Link>
            </div>
        </nav>
    );
}

export default Nav;