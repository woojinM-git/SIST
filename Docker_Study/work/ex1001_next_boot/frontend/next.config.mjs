/** @type {import('next').NextConfig} */
const nextConfig = {

    async rewrites(){
        return[
            {
                destination: "http://localhost/api/:path*",
                source: "/api/:path*"
            }
        ]
    }

};

export default nextConfig;
