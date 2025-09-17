/** @type {import('next').NextConfig} */
const nextConfig = {

    async rewrites(){
        return[
            {
                source: "/makeup/:path*", /* makeup로 시작하는 모든 경로는 */
                destination: "http://makeup-api.herokuapp.com/api/:path*"
            }
        ]
    }

};

export default nextConfig;
