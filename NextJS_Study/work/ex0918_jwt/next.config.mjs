/** @type {import('next').NextConfig} */
const nextConfig = {

    async rewrites(){
        return[
            {
                source: "/api/:path*",
                destination: "http://54.180.82.200:8080/api/:path*"
                // destination: "http://54.180.82.200/api/:path*"
            }
        ]
    }

};

export default nextConfig;
