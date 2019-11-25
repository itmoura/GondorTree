
<header>
    <nav class="navbar navbar-expand-lg navbar-light topBackground">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="container">
            <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                <a class="navbar-brand text-center" href="#"><i class="fas fa-dna text-white"></i></a>
                <form class="form-inline mr-auto mt-2 mt-lg-0 col-md-6">
                    <input class="form-control mr-sm-2 col-md-6" type="search" placeholder="Pesquisar" aria-label="Search">
                    <button class="btn btn-outline-light my-2 my-sm-0 " type="submit">Pesquisar</button>
                </form>
                <ul class="navbar-nav my-2 my-lg-0">
                    <li class="nav-item text-white">
                        <a class="nav-link text-white" href="member.htm?action=profile&id=<%=usess.getId() * 19819%>">
                            <img class="rounded-circle" src="https://storage.cloud.google.com/gondor-tree/profile/<%=usess.getPhoto()%>" alt="<%=usess.getName()%>" width="30" />
                            <%=usess.getName()%>
                        </a>
                    </li>
                    <li class="nav-item text-white">
                        <a class="nav-link text-white" href="#">Página Inicial</a>
                    </li>
                    <li class="nav-item text-white">
                        <a class="nav-link text-white" href="blazon.htm?action=list"><i class="fas fa-users"></i> Famílias</a>
                    </li>
                    <li class="nav-item text-white">
                        <a class="nav-link text-white" href="member.htm?action=logout"><i class="fas fa-power-off"></i> Sair</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
    
    