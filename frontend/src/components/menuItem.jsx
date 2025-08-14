export function MenuItem(props) {
    return(
        <li>
            <a href="#" className="block py-2 pr-4 pl-3 text-black rounded bg-primary-700 lg:bg-transparent lg:text-primary-700 lg:p-0 dark:text-white" aria-current="page">{props.children}</a>
        </li>
    )
}