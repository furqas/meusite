import HomeLayout from "../home/layout";

export default function PageLayout({
    children,
}: {
    children: React.ReactNode;
}) {

    return (
        
        <div>
                    {children}
                
        </div>
        
    )

}