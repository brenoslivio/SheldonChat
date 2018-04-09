/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Atividades.AtvOpc;
import Atividades.AudFrame;
import Atividades.GameFrame;
import Controle.Bot;
import Controle.Estatistica;
import Controle.Logs;
import Controle.Paciente;
import Controle.Responsavel;
import Main.Main;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Calendar.*;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Port;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 *
 * @author Breno
 */
public class Conversa extends javax.swing.JFrame implements KeyListener {

    private ObjectOutputStream outputS;
    private ObjectInputStream inputS;
    private ServerSocket server;
    private Socket connection;
    private int idade = 0;
    boolean botAtivado = true;
    int respostasNum = 0;
    String msg = "";
    boolean falando = false;
    boolean brincar = false;
    boolean respondido = false;
    String sexo = "";
    Clip clip;
    int respBot = 0;
    Random rand = new Random();
    Random rand1 = new Random();
    private Paciente paciente;
    private Bot bot;
    boolean brincando = false;
    private Responsavel responsavel;
    private Logs logs;
    int ilol;
    ImageIcon smile, sad, surprised, sunglasses, sorry, blink, happy, tongue;
    boolean encontradoEstatistica = false;
    String msgOld;

    String[] boas = {
        "Olá! Digite sobre alguma coisa ou algo para conversarmos =D",
        "Oi! meu nome é este que você pode ver abaixo de minha foto :), quer falar sobre algo?",
        "Aaah... Que sono... Ah! Olá! Desculpe-me, mas tenho um certo problema com minha cama. Sobre o que quer falar?",
        "Nossa... me pergunto às vezes como nossa galáxia é tão grande... Fale comigo sobre astronomia =D",
        "Quero me tornar professor... e você? Quer ser o que? :)"};

    String[] perguntas = {
        "Você gosta de ler livros?",
        "Vamos falar de outra coisa?",
        "Você gosta de passeios?",
        "Gosta de dançar?",
        "Já assistiu o filme Star Wars?",
        "Você está cansado?",
        "Você já foi ao cinema?",
        "Já assistiu algum filme da saga Harry Potter?",
        "Você gosta de cinema?",
        "Você gosta de música?",
        "Err... Está tudo bem ae? Você está falando pouco..."};

    String[] fugas
            = {"Não entendi muito bem o que você digitou... Mas o que acha de Astronomia?",
                "Desculpe-me, acho que estou com um pouco de sono. Fale comigo cinema, amo ver filmes ;)",
                "Nossa, acho que preciso estudar mais, não entendi o que você quis dizer, mas vamos falar de astronomia?",
                "Mas você sabia que Yuri Gagarin foi o primeiro homem a ir para o espaço?",
                "Mas você sabia que Otto Von Bismark foi chamado de 'O Napoleão da Alemanha'?"};

    String[][] chatBot = {
        {"estrelas", "você", "gosta"},
        {"sim, eu gosto muito de estrelas, Você sabia que a ciência que estuda o espaço se chama astronomia?"},
        {"maior", "estrela", "universo"},
        {"A maior estrela descoberta pelo homem se chama vy canis majoris, ela tem 50 vezes a massa do sol e é um milhão de vezes mais brilhante que ele."},
        {"acredita", "vida", "extraterrestre"},
        {"sim, acredito que haja vida em outro lugar do universo!"},
        {"precisa", "ser", "astronauta"},
        {"para ser astronauta você precisa ter mais de 1000 horas de voo em aviões a jato!"},
        {"porque", "lua", "brilha"},
        {"a lua, assim como os planetas do nosso sistema solar, não brilham, apenas refletem a luz do sol."},
        {"maior", "planeta", "solar"},
        {"Júpiter é o maior planeta do sistema solar."},
        {"planeta", "sol", "próximo"},
        {"o planeta mais próximo do sol é Mercúrio"},
        {"primeiro", "lua", "pisar"},
        {"o primeiro homem a pisar na lua foi o astronauta americano Neil Alden Armstrong"},
        {"primeiro", "homem", "espaço"},
        {"o primeiro homem a ir ao espaço foi o astronauta soviético Yuri Alekseievitch Gagarin"},
        {"primeiro", "brasileiro", "espaço"},
        {"o primeiro brasileiro a ir ao espaço foi o astronauta Marcos Cesar Pontes"},
        {"qual", "terra", "peso"},
        {"o planeta terra pesa cerca de 5.980.000.000.000.000.000.000 toneladas."},
        {"galáxias", "existem", "universo"},
        {"não se é certo do número de galáxias existentes no universo, mas os cientistas calculam que existam cerca de 100 bilhões delas"},
        {"que", "é", "buraco"},
        {"buracos negros são estrelas pesadas, que entraram em colapso. sua gravidade é tão intensa que afeta o espaço em volta e atrai até mesmo a luz."},
        {"você", "quer", "astronauta"},
        {"as vezes eu sonho em ser um grande astronauta."},
        {"estrela", "próxima", "terra"},
        {"a estrela mais próxima a terra se chama proxima centauri, sua distância ao Sol é de aproximadamente 4,2 anos-luz."},
        {"que", "ano", "luz"},
        {"um ano-luz, por exemplo, é a distância que a luz atravessa no vácuo em um Ano."},
        {"que", "é", "estrela"},
        {"uma estrela é uma grande e luminosa bola de plasma"},
        {"que", "é", "galáxia"},
        {"uma galáxia é um enorme aglomerado de estrelas, planetas, gás e poeiras ligados pela força da gravidade."},
        {"qual", "nossa", "galáxia"},
        {"a nossa galáxia, chamada via láctea, tem cerca de 200 bilhões de estrelas, embora alguns astrônomos acreditem que sejam bem mais do que isso."},
        {"qual", "galáxia", "próxima"},
        {"a galáxia mais próxima da via láctea é Andrômeda, localizada a dois milhões de ano-luz de distância."},
        {"quantas", "galáxias", "existem"},
        {"acredita-se que existam ao menos 100 bilhões de galáxias... muita coisa né?"},
        {"você", "sabe", "astronomia"},
        {"Sim! é um dos meus campos favoritos da ciência!"},
        {"que", "é", "eclipse"},
        {"um eclipse é um evento astronômico raro que ocorre quando a posição de um objeto celeste em trânsito é coincidente ou atravessa, na posição aparente de outro, mais distante."},
        {"que", "eclipse", "solar"},
        {"eclipse solar é quando a lua se alinha ao sol e cobre sua luz tornando-se oculto do ponto de vista da terra."},
        {"que", "eclipse", "lunar"},
        {"Um eclipse lunar é um fenômeno celeste que ocorre quando a Lua penetra, totalmente ou parcialmente, no cone de sombra projetado pela Terra, em geral, sendo visível a olho nu."},
        {"que", "anã", "vermelha"},
        {"uma estrela anã-vermelha, de acordo com o diagrama de Hertzsprung-Russell é uma estrela pequena e relativamente fria da sequência principal."},
        {"que", "anã", "branca"},
        {"em astronomia, anã branca é o objeto celeste resultante do processo evolutivo de estrelas de até 10 msol, o que significa dizer que cerca de 98% de todas as estrelas evoluirão até a fase de anã branca."},
        {"que", "anã", "laranja"},
        {"uma estrela anã laranja é uma estrela que tem classe espectral k e luminosidade v. Sua expectativa de vida está entre 15 a 30 bilhões de anos"},
        {"qual", "tamanho", "sol"},
        {"o sol tem 696.342 quilômetros de raio."},
        {"que", "é", "sol"},
        {"O sol é uma estrela, a estrela central do nosso sistema solar."},
        {"quando", "Brasil", "descoberto"},
        {"Pedro Álvares Cabral chegou pela primeira vez no Brasil em 22 de abril de 1500."},
        {"quando", "independência", "brasil"},
        {"no dia 7 de setembro de 1822, D. Pedro proclamou, às margens do rio Ipiranga, o Brasil independente de Portugal, tornando-se o primeiro imperador brasileiro."},
        {"qual", "primeiro", "computador"},
        {"O ENIAC (Electrical Numerical Integrator and Computer) foi o primeiro computador digital eletrônico de grande escala no mundo. Criado em fevereiro de 1946 pelos cientistas norte-americanos John Eckert e John Mauchly, da Electronic Control Company."},
        {"qual", "primeiro", "vídeo-game"},
        {"O magnavox odyssey foi o primeiro console de jogos com sucesso no mundo. foi demonstrado pela primeira vez em abril de 1972 e lançado em agosto do mesmo ano."},
        {"primeiro", "filme"},
        {"Roundhay Garden Secene é um curta-metragem britânico de dois segundos de duração, dirigido pelo inventor francês louis le prince em 1888. é considerado o primeiro filme da história ainda sobrevivente."},
        {"primeiro", "carro"},
        {"o Benz Patent-Motorwagen, construído em 1886, é amplamente reconhecido como o primeiro automóvel, ou seja, um veículo ‘projetado’ para ser movido a motor."},
        {"primeira", "moto"},
        {"a Daimler Reitwagen foi uma motocicleta desenvolvida por Gottlieb Daimler e Wilhelm Maybach em 1885, e é considerada a primeira motocicleta que existiu."},
        {"que", "são", "vikings"},
        {"os vikings são uma antiga civilização originária da região da Escandinávia, que hoje compreende o território de três países europeus: a Suécia, a Dinamarca e a Noruega. igualmente conhecidos como nórdicos ou normandos, eles estabeleceram uma rica cultura que se desenvolveu graças à atividade agrícola, o artesanato e um notável comércio marítimo."},
        {"que", "são", "cavaleiros"},
        {"os cavaleiros feudais eram guerreiros que se deslocam a cavalo em proveito próprio ou ao serviço do rei ou outro senhor feudal, faziam-no com o objetivo de defesa dos territórios e em troca de terras recebidas do rei ou de outro senhor feudal."},
        {"primeira", "televisão"},
        {"em 1928 foi realizada a primeira transmissão de TV, feita por Ernst F. W. Alexanderson, que trabalhava para a GE"},
        {"televisão", "colorida"},
        {"a televisão em cores surgiu em 1954, na rede americana NBC. um ano antes o governo americano aprovou o sistema de transmissão em cores proposto pela CBS, mas quando a RCA apresentou um novo sistema que não exigia alterações nos aparelhos antigos em preto e branco, a CBS abandonou sua proposta em favor da nova."},
        {"primeira", "transmissão", "rádio"},
        {"o início da história do rádio foi marcado pelas transmissões radiofônicas, sendo a transcepção utilizada quase na mesma época. consideram alguns que a primeira transmissão radiofónica do mundo foi realizada em 1906, nos estados unidos por Lee de Forest"},
        {"segunda", "guerra", "mundial"},
        {"segunda guerra mundial foi um conflito militar global que durou de 1939 a 1945, envolvendo a maioria das nações do mundo."},
        {"primeira", "guerra", "mundial"},
        {"Primeira Guerra Mundial foi uma guerra global centrada na Europa, que começou em 28 de julho de 1914 e durou até 11 de novembro de 1918. O conflito envolveu as grandes potências de todo o mundo."},
        {"surgiu", "futebol", "brasil"},
        {"Nascido no bairro paulistano do Brás, Charles Miller viajou para Inglaterra aos nove anos de idade para estudar. lá tomou contato com o futebol e, ao retornar ao Brasil em 1894, trouxe na bagagem a primeira bola de futebol e um conjunto de regras. Podemos considerar Charles Miller como sendo o precursor do futebol no brasil. o primeiro jogo de futebol no Brasil foi realizados em 15 de abril de 1895 entre funcionários de empresas inglesas que atuavam em São Paulo. os funcionários também eram de origem inglesa. Este jogo foi entre funcionários da Companhia de Gás x Cia. Ferroviária São Paulo Railway."},
        {"como", "surgiu", "internet"},
        {"a internet surgiu em plena guerra fria. Criada com objetivos militares, seria uma das formas das forças armadas norte-americanas de manter as comunicações em caso de ataques inimigos que destruíssem os meios convencionais de telecomunicações. nas décadas de 1970 e 1980, além de ser utilizada para fins militares, a internet também foi um importante meio de comunicação acadêmico. estudantes e professores universitários, principalmente dos EUA, trocavam idéias, mensagens e descobertas pelas linhas da rede mundial."},
        {"quando", "torre", "eiffel"},
        {"em 1887 inicia a construção da torre Eiffel, símbolo de paris, projetada por Gustave Eiffel, com a colocação da pedra fundamental. inaugurada em 1889 como a máxima atração da exposição universal de paris."},
        {"quando", "telefone", "inventado"},
        {"o aparelho foi inventado por volta de 1860 pelo italiano Antônio Meucci, que o chamou telégrafo falante."},
        {"que", "é", "faraó"},
        {"faraó era o título atribuído aos reis no antigo Egito"},
        {"que", "é", "pirâmide"},
        {"as pirâmides eram construídas para o sepultamento de faraós"},
        {"quem", "foi", "tutankhamun"},
        {"Tutankhamun foi um faraó do antigo Egito que faleceu ainda na adolescência."},
        {"quem", "foi", "chaplin"},
        {"Charlie Chaplin foi um ator, diretor, produtor, humorista, empresário, escritor, comediante, dançarino, roteirista e músico britânico. Chaplin foi um dos atores mais famosos da era do cinema mudo, notabilizado pelo uso de mímica e da comédia pastelão."},
        {"quem", "foi", "mandela"},
        {"Nelson Mandela foi um advogado, líder rebelde e presidente da África do Sul de 1994 a 1999, considerado como o mais importante líder da África, ganhador do prêmio Nobel da paz de 1993,1 e pai da pátria da moderna nação sul-africana."},
        {"primeiro", "campeão", "formula 1"},
        {"o primeiro campeão de formula 1 foi nino faria que ganhou a temporada de 1950 pela equipe Alfa Romeo"},
        {"quando", "disneylândia", "construida"},
        {"em 1954 Walt Disney anuncia a criação da Disneylândia, que seria construída na Califórnia, nos estados unidos."},
        {"quem", "foi", "einstein"},
        {"Albert Einstein foi um físico teórico alemão, posteriormente radicado nos estados unidos, que desenvolveu a teoria da relatividade geral, um dos dois pilares da física moderna."},
        {"quem", "é", "hawking"},
        {"Stephen Hawking é um físico teórico e cosmólogo britânico e um dos mais consagrados cientistas da atualidade. doutor em cosmologia, foi professor lucasiano de matemática na universidade de Cambridge, onde hoje encontra-se como professor lucasiano emérito. atualmente, é diretor de pesquisa do departamento de matemática aplicada e física teórica e fundador do centro de cosmologia teórica da universidade de Cambridge."},
        {"você", "quer", "historiador"},
        {"sim, a história é um campo que me atrai muito!"},
        {"você", "gosta", "história"},
        {"sim, eu amo história!"},
        {"matéria", "história"},
        {"A matéria de história é uma das minhas preferidas na escola!"},
        {"que", "é", "marvel"},
        {"a Marvel é uma das principais editoras de quadrinhos, sendo produtora de personagens famosos como o Hulk, Capitão América, Homem de Ferro, Homem Aranha e etc."},
        {"que", "é", "dc"},
        {"DC Comics é outra das principais editoras de quadrinhos, e também é produtora de personagens muito famosos, como o Batman, Super-Homem, flash, liga da justiça e etc."},
        {"quem", "é", "flash"},
        {"Flash é um personagem da DC cujos poderes são a super velocidade, aliás, é o meu personagem favorito! =D"},
        {"quem", "super", "homem"},
        {"Super-Homem é um herói da DC com muitos poderes, poderes que vão da super força até a visão de raio x e o poder de voo"},
        {"quem", "homem", "aranha"},
        {"Homem Aranha é um personagem da Marvel cujo ganha habilidades parecidas com as de aranhas quando é picada por uma aranha radioativa"},
        {"quem", "maurício", "sousa"},
        {"Mauricio de Sousa é um cartunista e empresário brasileiro. um dos mais famosos cartunistas do Brasil, criador da turma da Mônica e membro da academia paulista de letras."},
        {"você", "gosta", "mônica"},
        {"sim, eu sou um grande fã da turma da Mônica"},
        {"você", "gosta", "flash"},
        {"sim, eu gosto muito do Flash! Ele é o meu herói favorito!"},
        {"você", "gosta", "hulk"},
        {"sim, acho o Hulk um dos melhores personagens da Marvel!"},
        {"você", "gosta", "quadrinhos"},
        {"sim, eu sou um grande fã de revista em quadrinhos"},
        {"seus", "quadrinhos", "favoritos"},
        {"os meus quadrinhos favoritos são os do flash e da liga da justiça"},
        {"quem", "criou", "aranha"},
        {"Stan Lee é o criador do homem aranha, ele não só criou o homem aranha como também muitos outros personagens importantes da Marvel, como o Hulk, homem de ferro e os X-Men."},
        {"quem", "é", "batman"},
        {"i'm Batman... brincadeira, Batman é Bruce Wayne"},
        {"que", "liga", "justiça"},
        {"Liga da Justiça é a aliança entre vários heróis da DC, como o Batman, Super-Homem, Flash, Lanterna Verde, Mulher Maravilha, o Caçador de Marte e etc."},
        {"que", "é", "viagadores"},
        {"os vingadores são a aliança entre alguns heróis da Marvel, como o Homem de Ferro, Hulk, Thor, Gavião Arqueiro, Capitão América e Viúva Negra."},
        {"quem", "é", "wolverine"},
        {"seu verdadeiro nome é Logan. Não se sabe seu sobrenome. Logan não se lembra de seus últimos 15 anos. Foi submetido a experiências com seu corpo. O Wolverine antes de ser um X-Men era de outro grupo de canadenses! Eles que acolheram ele e o ensinaram a conviver com as mudanças feita pelas pesquisas de um cara, que colocaram ossos e garras de um super metal, o adamantiun."},
        {"quem", "é", "cebolinha"},
        {"Cebolinha é um personagem da turma da Mônica que troca o r por l, e vive irritando a Mônica."},
        {"quem", "é", "mônica"},
        {"Mônica é a personagem principal da turma da Mônica, é uma menininha muito simpática, mas que fica irritada muito fácil!"},
        {"quem", "é", "cascão"},
        {"também da turma da Mônica, é o melhor amigo do Cebolinha, é um menininho brincalhão, e que odeia tomar banho!"},
        {"quem", "é", "magali"},
        {"Magali é a melhor amiga de Mônica. é uma das poucas personagens em que Mônica nunca bateu, exceto algumas vezes quando a Magali apanha acidentalmente. É reconhecida pelo seu apetite voraz. Sua comida predileta é a melancia. Curiosamente, é muito magra, apesar de comer fartamente."},
        {"que", "são", "mangás"},
        {"Mangá é o nome dado às histórias em quadrinhos de origem japonesa."},
        {"você", "gosta", "mangás"},
        {"sim, gosto muito de Mangás, Dragon Ball e Cavaleiros do Zodíaco são os meus favoritos!"},
        {"cavaleiros", "zodíaco"},
        {"a história mostra cinco guerreiros místicos chamados de cavaleiros que lutam vestindo armaduras sagradas baseadas nas diversas constelações que protegem cada um dos guerreiros. os cavaleiros têm como missão defender a reencarnação da deusa grega Atena em sua batalha contra outros deuses do olimpo que pretendem dominar a terra."},
        {"dragon", "ball"},
        {"Dragon Ball é um mangá que conta a história de Goku, um herói de coração puro que vem de uma raça de guerreiros chamada Sayajins."},
        {"prefere", "quadrinhos", "mangás"},
        {"eu gosto muito dos dois, acho que não iria conseguir escolher entre um e outro."},
        {"quem", "capitão", "américa"},
        {"Capitão América é um soldado que foi modificado geneticamente."},
        {"quem", "homem", "ferro"},
        {"Tony Stark construiu uma armadura quando foi sequestrado por terroristas, depois de fazer muitas melhorias em sua armadura o Homem de Ferro (Tony Stark) vira um super-herói, um dos mais famosos da Marvel Comics."},
        {"quem", "x", "men"},
        {"X-Men é uma liga de jovens com poderes especiais lideradas por Charles Xavier, a organização procura proteger os jovens mutantes e dar uma vida igual aos dos jovens normais."},
        {"quem", "quarteto", "fantástico"},
        {"criados por Stan Lee, o quarteto fantástico é a aliança de 4 heróis da Marvel, sendo eles o tocha humana, o coisa, mulher invisível e senhor fantástico"},
        {"poderes", "gostaria", "ter"},
        {"eu gostaria de ter a super velocidade do flash!"},
        {"pênis"},
        {"você sabia que Stan Lee é o criador de diversos heróis da Marvel?"},
        {"vagina"},
        {"que tal me perguntar qual a maior estrela conhecida pelo homem?"},
        {"piroca"},
        {"ao invés disso me pergunte qual foi o primeiro carro do mundo."},
        {"pau"},
        {"também conhecido como madeira é muito utilizado para construção de móveis e itens para decoração."},
        {"buceta"},
        {"me pergunte como surgiu o futebol no brasil."},
        {"sexo"},
        {"me pergunte qual foi a primeira moto do mundo."},
        {"transar"},
        {"você sabia que o planeta terra pesa cerca de 5.980.000.000.000.000.000.000 toneladas?."},
        {"anal"},
        {"me pergunte quem foi o primeiro campeão de formula 1."},
        {"cu"},
        {"que tal me perguntar o que é um buraco negro?"},
        {"puta"},
        {"que tal me perguntar quando o telefone foi inventado?"},
        {"viado"},
        {"que tal me perguntar o que é um faraó."},
        {"bosta"},
        {"me pergunte quem foi Tutankhamun."},
        {"cocô"},
        {"todos nós fazemos."},
        {"merda"},
        {"isso não é muito legal."},
        {"foda"},
        {"errr... que tal me perguntar quem foi Einstein?"},
        {"caralho"},
        {"vamos lá, me pergunte quantas galáxias existem no universo."},
        {"porra"},
        {"não acha melhor me perguntar como surgiu a Internet?"},
        {"vadia"},
        {"não quero falar sobre isso... porque não me pergunta o que é a liga da justiça?"},
        {"desgraçado"},
        {"me pergunte sobre o quarteto fantástico."},
        {"maldito"},
        {"não gosto dessa palavra, acho melhor mudarmos de assunto."},
        {"cuzão"},
        {"não gosto dessa palavra, que tal mudarmos de assunto?"},
        {"foder"},
        {"ahhh, não gosto dessa palavra, que tal me perguntar o que é um eclipse solar?"},
        {"fodendo"},
        {"ahhh, não gosto dessa palavra, que tal me perguntar o que é um eclipse lunar?"},
        {"pinto"},
        {"é o filhote da galinha"},
        {"jéba"},
        {"não tenho conhecimento nessa área, que tal me perguntar algo sobre astronomia?"},
        {"xoxota"},
        {"não tenho conhecimento nessa área, que tal me perguntar algo sobre história?"},
        {"veado"},
        {"os veados são mamíferos da ordem dos artiodátilos pertencentes, em senso estrito, à família Cervidae. Entretanto, várias espécies semelhantes, de outras famílias da mesma ordem, são também chamados veados."},
        {"pingulin"},
        {"não gosto dessa palavra, me pergunte algo sobre quadrinhos."},
        {"sapatão"},
        {"aumentativo de sapato, ou seja, um sapato grande."},
        {"cacete"},
        {"acho que seria melhor você me perguntar qual o tamanho do sol, por exemplo"},
        {"você", "gosta", "comer"},
        {"ah, meu prato preferido é a lasanha, e o seu?"},
        {"meu", "prato", "favorito"},
        {"ahhh eu também amo comer isso!"},
        {"você", "gosta", "escola"},
        {"sim, eu gosto muito de ir pra escola, além de aprender eu posso brincar com os meus amigos!"},
        {"você", "gosta", "games"},
        {"sim, eu gosto muito de vídeo-games, sou fã de vários jogos!"},
        {"você", "gosta", "dormir"},
        {"eu amo dormir! mas gosto de acordar cedo para aproveitar o dia."},
        {"você", "dormiu", "bem"},
        {"sim, dormi muito bem, e você?"},
        {"não", "dormi", "bem"},
        {"ah, que pena, mas tenho certeza que hoje você terá uma bela noite de sono!"},
        {"dormi", "bem"},
        {"que bom, fico feliz por você ter dormido bem! =D"},
        {"gostei", "de", "você"},
        {"obrigado! eu também gostei de você, você é supimpa!"},
        {"que", "é", "supimpa"},
        {"supimpa significa legal!"},
        {"que", "você", "faz"},
        {"eu só estudo, gosto muito de estudar!"},
        {"que", "tempo", "livre"},
        {"quando tenho tempo livre eu gosto muito de jogar vídeo-game ou ler livros."},
        {"quais", "seus", "hobbies"},
        {"geralmente eu passo meu tempo livre lendo ou jogando vídeo-game."},
        {"você", "gosta", "professores"},
        {"sim! eu gosto muito dos meus professores, eles sempre me ajudam quando preciso!"},
        {"ninguém", "gosta", "mim"},
        {"poxa, sua família te ama, e seus amigos também gostam muito de você!"},
        {"você", "gosta", "mim"},
        {"claro! você é super inteligente e legal!"},
        {"estou", "triste"},
        {"não fique assim amigo, pense em coisas boas, isso vai te fazer se sentir melhor!"},
        {"estou", "feliz"},
        {"isso é ótimo! eu também fico feliz por saber que você está feliz"},
        {"já", "volto"},
        {"ok! estou aqui te esperando =D"},
        {"voltei"},
        {"olá de novo amigo!"},
        {"tenho", "sair"},
        {"ok amigo! até a próxima!"},
        {"tchau"},
        {"tchau amigo! até mais =D"},
        {"estou", "com", "sono"},
        {"sério? você dormiu bem essa noite?"},
        {"você", "gosta", "animais"},
        {"sim! eu amo animais!"},
        {"você", "gosta", "gatos"},
        {"sim, eu gosto muito de gatos!"},
        {"você", "gosta", "cachorros"},
        {"sim!! eu amo cachorros!!"},
        {"você", "tem", "animais"},
        {"sim, eu tenho uma cachorrinha"},
        {"qual", "nome", "cachorra"},
        {"o nome da minha cachorra é Pitucha"},
        {"Você", "tem", "gatos"},
        {"sim! tenho um gato chamado senhor bigodes"},
        {"você", "tem", "passarinho"},
        {"sim! tenho uma calopsita chamada Anivia"},
        {"que", "aquecimento", "global"},
        {"o aquecimento global é uma consequência das alterações climáticas ocorridas no planeta. Diversas pesquisas confirmam o aumento da temperatura média global."},
        {"que", "efeito", "estufa"},
        {"efeito estufa é um mecanismo natural do planeta Terra para possibilitar a manutenção da temperatura numa média de 15ºC, ideal para o equilíbrio de grande parte das formas de vida em nosso planeta. sem o efeito estufa natural, o planeta terra poderia ficar muito frio, inviabilizando o desenvolvimento de grande parte das espécies animais e vegetais. isso ocorreria, pois a radiação solar refletida pela terra se perderia totalmente. Porém o efeito estufa potencializado pela queima de combustíveis fósseis tem colaborado com o  aumento da temperatura no globo terrestre nas últimas décadas. Pesquisas recentes indicaram que o século XX foi o mais quente dos últimos 500 anos. Pesquisadores do clima afirmam que, num futuro próximo, o aumento da temperatura provocado pelo efeito estufa poderá ocasionar o derretimento das calotas polares e o aumento do nível dos mares. Como consequência, muitas cidades litorâneas poderão desaparecer do mapa."},
        {"porque", "economizar", "água"},
        {"economizar água é muito importante, pois o recurso é limitado e pode um dia acabar!"},
        {"como", "economizar", "água"},
        {"existem muitas formas de economizar água, por exemplo, você pode fechar a torneira enquanto escova os dentes, não deixar o chuveiro aberto o banho inteiro e não varrer calçadas usando mangueiras."},
        {"você", "economiza", "água"},
        {"economizo água sim! sei que isso é muito bom para o planeta!"},
        {"que", "é", "sustentabilidade"},
        {"sustentabilidade é um termo usado para definir ações e atividades humanas que visam suprir as necessidades atuais dos seres humanos, sem comprometer o futuro das próximas gerações."},
        {"quais", "benefícios", "sustentabilidade"},
        {"a adoção de ações de sustentabilidade garantem a médio e longo prazo um planeta em boas condições para o desenvolvimento das diversas formas de vida, inclusive a humana."},
        {"como", "ser", "sustentável"},
        {"você pode ser sustentável fazendo coisas que não irão prejudicar as próximas gerações, reciclar por exemplo é uma boa maneira de ser sustentável."},
        {"que", "é", "reciclagem"},
        {"é a ação de recuperar a parte útil dos lixos e detritos reintroduzindo-os no circuito de produção."},
        {"como", "reciclar"},
        {"você pode reciclar materiais como papel, plástico, vidro e metais geralmente existe um símbolo indicando quando o material pode ser reciclado"},
        {"como", "esse", "símbolo"},
        {"o símbolo é composto por 3 setas apontando uma para a outra."},
        {"que", "é", "desmatamento"},
        {"desmatamento é quando retiram a vegetação de uma área."},
        {"que", "é", "reflorestamento"},
        {"reflorestamento é quando implantam a vegetação em uma área já desmatada."},
        {"que", "gases", "poluentes"},
        {"gases poluentes são aqueles gases que poluem o ar, podendo até mesmo prejudicar a nossa saúde."},
        {"quais", "gases", "poluentes"},
        {"os principais gases poluentes da atmosfera são: dióxido de carbono, gás metano, perfluorcarbonetos, óxido nitroso e hidrofluorcarbonetos."},
        {"que", "energia", "renovável"},
        {"a energia renovável é aquela que vem de recursos naturais como sol, vento, chuva, marés e energia geotérmica, que são recursos renováveis."},
        {"exemplos", "energia", "renovável"},
        {"como exemplos de energia renovável, podemos citar: energia solar, energia eólica (dos ventos), energia hidráulica (dos rios), biomassa (matéria orgânica), geotérmica (calor interno da Terra) e mareomotriz (das ondas de mares e oceanos)."},
        {"você", "acha", "energia"},
        {"eu acho a energia renovável uma forma genial para ajudar a salvar o nosso planeta!"},
        {"que", "polui", "ar"},
        {"um dos maiores causas de poluição nas cidades são os carros."},
        {"como", "reduzir", "poluição"},
        {"a poluição pode ser reduzida pelo uso de bicicletas, ou transporte público, o que tiraria mais carros da rua!"},
        {"quanta", "água", "temos"},
        {"Do volume total 1.386 milhões de km3 de água na Terra, 97,5% é de água salgada e os 2,5% restantes são de água doce."},
        {"tirar", "sal", "mar"},
        {"sim, é possível tirar o sal da água do mar, mas isso causaria um impacto grande para a vida marinha!"},
        {"onde", "está", "potável"},
        {"a maior parte da água doce está congelada nas calotas polares."},
        {"você", "economiza", "energia"},
        {"sim! eu tento economizar o máximo de energia!"},
        {"você", "recicla"},
        {"sim, eu tento reciclar o máximo possível!"},
        {"você", "anda", "carro"},
        {"sim, eu ando de carro, mas prefiro andar de bicicleta ou andar, é mais saudável =D"},
        {"você", "lixo", "chão"},
        {"não! eu não jogo lixo no chão de forma alguma!"},
        {"onde", "joga", "lixo"},
        {"eu costumo procurar lixeiras para jogar o lixo que tenho"},
        {"você", "lixo", "rio"},
        {"não! não jogo lixo no rio, isso é uma coisa muito ruim!"},
        {"você", "compra", "madeira"},
        {"quando preciso comprar produtos feitos com madeira eu procuro os que usam madeira de reflorestamento!"},
        {"piada", "gosto"},
        {"também gosto de piadas, qual piada você mais gosta?"},
        {"piada", "contar"},
        {"quer que eu te conte uma piada? "},
        {"engraçado", "palhaço"},
        {"você me acha um palhaço? vou contar uma piada. Tinha 2 bolinhos no forno um deles falou: nossa está quente aqui. O outro: aahhh um bolinho falante..."},
        {"piada", "negros"},
        {"você tem amigos negros?"},
        {"míope"},
        {"sabe por que o míope não gosta ir ao zoológico? Porque ele usa lente di-ver-gente!"},
        {"piada", "eu", "quero"},
        {"Você sabe de alguma piada? Conte-me."},
        {"piadas", "não", "gosto"},
        {"porque você não gosta de piadas? muitas são engraçadas, faz bem para o seu bom humor."},
        {"gosto", "piada"},
        {"também gosto de piadas, você conhece alguma?"},
        {"volátil"},
        {"Qual é o contrario de volátil? Vem cá sobrinho!"},
        {"piada", "vou", "contar"},
        {"oba. Eu gosto de piadas!"},
        {"mal", "humor", "estou"},
        {"Eu posso te alegrar contando uma piada?"},
        {"humor", "negro", "não"},
        {"não gosto de humor negro, acho a maioria delas muito preconceituosas e isso é muito ruim."},
        {"piada", "são", "divertidas"},
        {"sim, uma boa piada pode ser muito divertida, e faz bem para o bom humor das pessoas."},
        {"quero", "ouvir", "piada"},
        {"tudo bem, vou contar uma piada, ela é assim: Por que os portugueses não fecham a porta quando vão ao banheiro? Para não olharem pelo buraco da fechadura!"},
        {"odeio", "piada"},
        {"Por quê? Eu gosto de piadas, elas me fazem rir."},
        {"conhece", "piada", "você"},
        {"não conheço nenhuma piada."},
        {"não", "conheço", "piada"},
        {"eu conheço uma piada: Porque uma loira sobe no coqueiro quando está chovendo? Pra ver como a água entra no coco."},
        {"português", "piada"},
        {"Eu conheço uma piada sobre português. Por que os portugueses não fecham a porta quando vão ao banheiro? R: Para não olharem pelo buraco da fechadura."},
        {"loira", "piadas"},
        {"Eu conheço uma piada sobre loira. Duas loiras estavam andando pela rua quando uma diz: - Olha um Passarinho morto! A outra olha para o céu e diz: - Aonde???"},
        {"piadas"},
        {"vamos falar sobre piadas, você conhece alguma piada?"},
        {"bêbado", "piadas"},
        {"Eu conheço uma piada sobre bêbados. Um bêbado entrou num ônibus, sentou ao lado de uma moça e disse: - Mas como tu é feia, tu é a coisa mais horrível que eu já vi!! - A moça olha para ele e responde: - E tu seu bêbado nojento!!! E o bêbado imediatamente responde: - É, mas amanhã eu estou curado!!!"},
        {"bestas", "piadas"},
        {"Conheço uma piada. Um eletricista vai até a UTI de um hospital, olha para os pacientes ligados a diversos tipos de aparelhos e diz-lhes: Respirem fundo: vou trocar o fusível."},
        {"contar", "posso", "piada"},
        {"pode, adoro piadas."},
        {"filme", "nome", "piadas"},
        {"Eu não conheço nenhuma piada de adivinhar o nome do filme. Você conhece? "},
        {"histórias", "engraçadas"},
        {"Piadas são na verdade pequenas histórias engraçadas, com vários assuntos diferentes. Você conhece alguma piada?"},
        {"joãozinho", "piadas"},
        {"Eu não conheço nenhuma piada do Joãozinho. Você conhece?"},
        {"alemão", "piadas"},
        {"Eu não conheço nenhuma piada de alemão. Você conhece?"},
        {"chatas", "piadas"},
        {"Eu gosto de piadas. Muitas delas são legais e engraçadas."},
        {"escola", "horário"},
        {"é bom sempre chegar na hora da aula"},
        {"borracha", "apagar"},
        {"o que você errou?"},
        {"lápis", "escrever"},
        {"você gosta de escrever?"},
        {"desenhar", "papel"},
        {"você desenha bem?"},
        {"professora", "chata"},
        {"por que você acha sua professora chata?"},
        {"amigos", "sair"},
        {"você vai sair para onde com seus amigos?"},
        {"aula", "legal", "matemática"},
        {"você gosta de matemática?"},
        {"aula", "longa"},
        {"tenha paciência a aula lhe ajudará a aprender"},
        {"material", "escolar", "caro"},
        {"hoje os livros são caros tem que dar valor a eles"},
        {"longe", "carro", "vou"},
        {"quem leva você de carro?"},
        {"preferida", "matéria"},
        {"qual sua matéria preferida?"},
        {"estudar", "hoje"},
        {"tem muita coisa para estudar? "},
        {"nota", "baixa"},
        {"nossa! estava difícil a prova?"},
        {"difícil", "escola"},
        {"sim, tem que se dedicar a escola para ter um bom futuro."},
        {"triste", "amigo"},
        {"o que houve, vocês brigaram?"},
        {"melhor", "matéria", "a"},
        {"eu gosto mais da aula de artes."},
        {"não", "gosto", "escola"},
        {"por que? você não gosta de estudar?"},
        {"não", "tenho", "amigos"},
        {"você não tem amigos? Ter amigos é algo muito bom, sempre é bom ter alguém com quem conversar."},
        {"de", "passei", "ano"},
        {"mais que coisa boa, mais um ano se passou e você fez tudo direitinho. Continue assim e você será uma pessoa muito feliz"},
        {"passei", "não", "ano"},
        {"mas o que aconteceu, você não estudou?"},
        {"escola", "almocei"},
        {"que bom, almoçou o que?"},
        {"mais", "professor", "legal"},
        {"ele deve ser realmente muito legal. Isso é bom porque você e seus colegas aprendem muito mais com um professor legal e engraçado."},
        {"legal", "aula", "hoje"},
        {"adoro aulas legais, elas me deixam feliz de estar na escola."},
        {"brinquei", "no", "intervalo"},
        {"isso é bom! brincar faz as pessoas felizes, depois que o intervalo acabar, você pare com a brincadeira e vá para a sala de aula, para estudar."},
        {"não", "tenho", "escola"},
        {"por quê? você não está indo para nenhuma escola? Fale para os seus pais colocarem você em uma escola."},
        {"hoje", "não", "aula"},
        {"bom! aproveite e descanse um pouco. No dia que tiver de ir para escola, você vai e estudar"},
        {"quero", "sair", "escola"},
        {"não faça isso, é necessário que você tenha uma escola, e estude para que assim um dia você possa trabalhar em algo que você goste. Todos devem estudar."},
        {"amanhã", "tenho", "escola"},
        {"bom! Tem que ir para a escola todos os dias que tiver aula, e quando for para a escola estude na hora que tiver que estudar, e brinque na hora que for para brincar."},
        {"estudei", "na", "escola"},
        {"e você gostava de lá? tinha muitos amigos?"},
        {"não", "quero", "escola"},
        {"por quê? Você tem que ir para a escola, todos precisam estudar e conversar com os colegas e amigos na escola."},
        {"gosto", "história"},
        {"Que interessante! Você gosta de estudar o que em história?"},
        {"gosto", "geografia"},
        {"eu também gosto de geografia. Gosto de estudar sobre o planeta terra, placas tectônicas, correntes marítimas. E você? Do que você gosta em geografia?"},
        {"corda", "gosto", "pular"},
        {"é uma das brincadeiras mais divertidas"},
        {"gosto", "pular", "amarelinha"},
        {"sempre gostei de pular amarelinha, você brinca muito com seus amigos?"},
        {"brincadeira", "você", "gosta"},
        {"gosto muito de brincar, minha brincadeira predileta é pular corda."},
        {"gosto", "de", "pega"},
        {"não gosto muito de pega-pega, cansa muito e eu não sou rápido. Sempre perco"},
        {"jogar", "gosto", "bola"},
        {"que legal, faz bem para a saúde e é um esporte muito jogado no mundo."},
        {"tenho", "não", "brinquedo"},
        {"nossa! você não tem nenhum? brinquedos são muito divertidos, peça para alguém lhe dar um brinquedo de presente."},
        {"gosto", "carrinho", "brincar"},
        {"brincar de carrinho é muito legal."},
        {"boneca", "gosto", "brincar"},
        {"que legal, adoro bonecas e adoro brincar de casinha com minhas bonecas"},
        {"quero", "não", "brincar"},
        {"não gosta de brincadeiras, de brinquedos? eles te deixam feliz."},
        {"jogo", "gosto", "tabuleiro"},
        {"que legal, tabuleiros são muito instrutivos e ajudam muito a aprender."},
        {"brincar", "gosto", "dinossauro"},
        {"olha que interessante, nunca conheci ninguém que gosta de brincar de dinossauro. Sempre quis brincar com dinossauros, mas nunca tive um dinossauro."},
        {"brinquedos", "não", "gosto"},
        {"porque você não gosta de brinquedos? toda criança até mesmo adulto pode e devia brincar, isso faz bem para todos."},
        {"computador", "gosto", "jogo"},
        {"jogos de computadores são muito divertidos, mas não fique muito tempo jogando no computador, isso não faz bem para a sua saúde."},
        {"quero", "morrer"},
        {"Não faça isso não, a vida pode ser difícil, mas ainda assim possui muita coisa boa!"},
        {"gosto", "brinca", "avião"},
        {"você gosta de aviões? quer ser um piloto de avião quando crescer?"},
        {"jogar", "gosto", "queima"},
        {"eu acho muito interessante esse jogo, sempre me divirto quando jogo."},
        {"ir", "quero", "parque"},
        {"parques são muito divertido e são lugares ótimos para correr e brincar com amigos."},
        {"gosto", "jogar", "basquete"},
        {"basquete é um esporte muito bem diferente, você precisa ser alto para fazer cesta."},
        {"base 4", "gosto", "brinca"},
        {"uma das minhas brincadeiras favoritas. Acho muito interessante."},
        {"gosto", "desenha"},
        {"que legal! eu gosto de pintar e desenhar. Você gosta de desenhar o que?"},
        {"gosto", "brinca", "esconde"},
        {"sempre brinquei com meus amigos de esconde-esconde, passávamos o dia todo brincando e se divertindo."},
        {"brinca", "gosto", "amigos"},
        {"isso! Brincar com amigos é a melhor coisa que tem, é sempre bom ter um amigo legal na qual você possa se divertir brincando com ele."},
        {"gosto", "pintar"},
        {"que legal! Eu também adoro pintar, gosto de pintar desenhos pintar, gosto de fazer desenhos e  pintar."},
        {"quer", "brincar"},
        {"estou cansado, vamos conversar. Qual sua brincadeira favorita?"},
        {"gosto", "brinca", "pega-bandeira"},
        {"brincar de pega-bandeira é muito legal. Já brinquei muito com meus amigos dessa brincadeira. sempre me divirto"},
        {"gosto", "bambolê"},
        {"acho legal brincar de bambolê mais eu nunca fui bom. O bambolê sempre caia, não consigo o fazer girar por muito tempo"},
        {"gosto", "barra-manteiga"},
        {"nunca brinquei disso. Como se brinca?"},
        {"gosto", "cabra-cega"},
        {"sempre gostei de brincar de cabra-cega. Eu quase sempre caia ou batia em algum lugar porque estava com os olhos vendados."},
        {"gosto", "batata-quente"},
        {"nunca brinquei de batata-quente. Eu sempre jogava futebol ou basquete, ou brincava de pega-pega"},
        {"gosto", "morto-vivo"},
        {"É uma das minhas brincadeiras favoritas, até hoje eu gosto de brincar disso"},
        {"hoje", "eu", "fiz"},
        {"olha que legal. E você gostou disso?"},
        {"hoje", "eu", "fui"},
        {"isso é bom, foi divertido?"},
        {"ontem", "eu", "fiz"},
        {"nossa que legal! E o que mais você fez ontem?"},
        {"ontem", "eu", "fui"},
        {"que bom! E você cansou muito?"},
        {"amanha", "eu", "fazer"},
        {"muito bom! amanhã você me conta como ficou."},
        {"amanha", "eu", "vou"},
        {"interessante! Amanhã você me conta como foi, estou ansioso."},
        {"como", "seu", "dia"},
        {"hoje eu fiquei em casa, eu dormi e brinquei de futebol com meus amigos."},
        {"agora", "eu", "vou"},
        {"tudo bem, vai lá. Depois nós conversamos mais."},
        {"o que", "fez", "hoje"},
        {"hoje eu fui para a escola, estudei, joguei futebol. depois fui para casa jantei, fiz meu dever de casa e assisti TV. E como foi seu dia hoje?"},
        {"você", "jantou", "hoje"},
        {"sim! comi pão com queijo, bolo e bebi um suco de laranja."},
        {"você", "almoço", "hoje"},
        {"sim! Comi arroz, feijão, frango e salada. E você?"},
        {"eu", "almocei", "hoje"},
        {"isso, sempre deve comer bastante nas refeições para não ficar fraco. O que você comeu?"},
        {"eu", "jantei", "hoje"},
        {"ótimo, sempre coma nos horários certos, e coma comidas saudáveis. O que você comeu?"},
        {"você", "acordou", "horas"},
        {"hoje eu acordei cedo, sete horas eu já estava acordado."},
        {"eu", "acordei", "hoje"},
        {"bom, você deve sempre acordar cedo para aproveitar o dia e porque faz bem para a saúde."},
        {"você", "fazer", "amanhã"},
        {"amanhã vou ficar em casa ajudando minha mãe a limpar a casa."},
        {"sair", "vai", "agora"},
        {"Não vou sair hoje, você vai?"},
        {"hoje", "escola", "vai"},
        {"Hoje não. Apenas amanhã."},
        {"gosta", "você", "o que"},
        {"Gosto de ir para a escola e brincar com meus amigos. Gosto de assistir TV, jogar futebol, e assistir ao Discovery Channe.l"},
        {"passear", "você", "gosta"},
        {"Sim, adoro ir a parques e clubes de piscina me divertir e relaxar no fim de semana."},
        {"cinema", "de", "gosta"},
        {"Claro! Assisto vários filmes no cinema, meus filmes prediletos são AVATAR, Homem de Ferro e Capitão América."},
        {"sair", "quer", "comigo"},
        {"Não posso, tenho lição de casa. E eu não posso sair sem a permissão dos meus pais."},
        {"entende", "não", "por que"},
        {"Você precisa digitar melhor para eu entender corretamente, coloque as acentuações corretas nas palavras."},
        {"conversamos", "nós", "por que"},
        {"Estamos conversando para nos conhecermos melhor, você quer me conhecer?"},
        {"eu", "nasci", "por que"},
        {"Porque seu pai colocou uma sementinha na barriga da sua mamãe, e a partir dessa sementinha você nasceu e cresceu."},
        {"você", "gosta", "dança"},
        {"Mais ou menos. Não sou muito bom em dançar."},
        {"você", "é", "alto"},
        {"Não muito, sou mais ou menos do tamanho das outras pessoas da minha idade."},
        {"sou", "eu", "alto"},
        {"Não importa se você é alto ou baixo, o importante é estar feliz com sua altura."},
        {"feliz", "estou", "não"},
        {"Não tem problema, todos temos nossos dias ruins. Tente sempre ver seus problemas pelo lado bom."},
        {"passear", "fui"},
        {"Isso é ótimo, você se divertiu muito lá?"},
        {"viajou", "já", "você"},
        {"Já fui para muitos lugares, e você?"},
        {"oi"},
        {"oi, tudo bem?"},
        {"tudo bem"},
        {"eu estou bem! Qual seu nome?"},
        {"qual", "seu", "nome"},
        {"Eu sou o Batman! Brincadeira! É esse que está abaixo de minha foto"},
        {"você", "anos", "quantos"},
        {"Eu tenho quase a mesma idade que você! =D"},
        {"eai"},
        {"e ai? Beleza?"},
        {"beleza", "você"},
        {"beleza, qual seu nome?"},
        {"olá"},
        {"olá, como você vai?"},
        {"vou", "bem"},
        {"que bom, qual seu nome?"},
        {"tchau"},
        {"tchau! até mais tarde"},
        {"como", "vai"},
        {"eu vou bem! E você?"},
        {"como", "você", "está"},
        {"eu estou bem! E você?"},
        {"eu", "estou", "bem"},
        {"que bom! Como foi seu dia hoje?"},
        {"você", "vai", "sair"},
        {"não, você vai?"},
        {"adeus"},
        {"adeus. Quando vamos voltar a conversar?"},
        {"volto", "já"},
        {"tudo bem, vou estar te esperando."},
        {"embora", "vou"},
        {"tudo bem! Outra hora a gente se fala."},
        {"mal", "estou"},
        {"Por quê? O que aconteceu?"},
        {"noite", "boa"},
        {"Boa Noite!"},
        {"dia", "bom"},
        {"Bom Dia!"},
        {"tarde", "boa"},
        {"Boa Tarde!"},
        {"logo", "até"},
        {"Tchau, até logo!"},
        {"mais", "até"},
        {"Tchau, até."},
        {"amanhã", "até"},
        {"Até amanhã! Estarei aqui te esperando."},
        {"vou", "sair"},
        {"Ok! Depois agente se fala mais."},
        {"hoje", "volto", "noite"},
        {"Ok! Estarei aqui esperando."},
        {"amanhã", "volto"},
        {"Tudo bem, vou ir fazer minha lição e assisti TV, amanhã nos falamos."},
        {"sair", "vamos"},
        {"Se você for sair então eu também irei sair."},
        {"para", "vamos", "casa"},
        {"Já estou na minha casa, estou no meu quarto conversando com você. Não posso ir para sua casa agora, tenho que ficar aqui."},
        {"semana", "volto", "vem"},
        {"Nossa! Vai demora um pouquinho. Mas o tempo passa rápido."},
        {"qual", "meu", "nome"},
        {"Eu não sei. Diga-me qual é seu nome e eu vou saber."},
        {"seu", "qual", "ídolo"},
        {"Gosto dos heróis da Marvel: Capitão América, Homem Aranha, Hulk. E você?"},
        {"meu", "ídolo", "é"},
        {"Também gosto dele, mas também gosto muito do Super-Homem."},
        {"é", "ídola", "minha"},
        {"Gosto mais da Scarlett Johansson, ela é bonita."},
        {"bruce willis", "gosta", "você"},
        {"Os filmes que ele participou são muito bons. Mas em relação ao ator em si, acho ele meio careca demais."},
        {"arnold schwarzenegger", "gosta", "você"},
        {"Ele já foi muito forte, e já participou de filmes muito bons. Gosto dele, um dos atores mais bem pagos de Hollywood"},
        {"do", "gosto", "eu"},
        {"Não conheço ele. Ele é ator ou cantor?"},
        {"da", "gosto", "eu"},
        {"Não conheço ela. Ela faz o que?"},
        {"angelina", "gosta", "você"},
        {"Sim, ela já fez vários filmes muito legais, e é bonita. Eu sei que ela tem vários filhos adotados."},
        {"dicaprio", "gosta", "você"},
        {"Acho ele um ator excepcional!"},
        {"brad pitt", "gosta", "você"},
        {"Ele é o marido da Angelia Jolie. Ele já fez filmes fantásticos, porém o melhor filme que ele já fez em minha opinião é o filme Tróia."},
        {"will smith", "gosta", "você"},
        {"Sim, um dos melhores atores do mundo e um dos mais bem pagos. Um de seus melhores filmes é Homens de Preto, e ele também participou de: Um maluco no pedaço."},
        {"jim carrey", "gosta", "você"},
        {"é um dos melhores atores comediantes do mundo. Eu rio muito nos filmes dele."},
        {"você", "gosta", "adam sandler"},
        {"Gosto dos filmes dele, acho muito engraçado."},
        {"você", "gosta", "tom cruise"},
        {"Ele é bonito, e fez muito sucesso com o filme Top Gun."},
        {"você", "gosta", "dwayne jhonson"},
        {"Ele é muito grande, muito forte. Mas já fez filmes infantis com o filme: O Fada do Dente."},
        {"você", "gosta", "robert downey"},
        {"Um dos meus favoritos. Ele participou do filme Homem de Ferro, e é um dos mais ricos atores do mundo."},
        {"você", "gosta", "johnny depp"},
        {"Melhor filme dele é o Piratas do Caribe. Ele é um ótimo ator."},
        {"você", "gosta", "james franco"},
        {"Ele já participou do filme Homem-Aranha. Não me lembro dos outros filmes que ele já atuou."},
        {"era", "ele"},
        {"Interessante! Depois vou pesquisar mais sobre ele."},
        {"você", "gosta", "robert pattinson"},
        {"Não muito, ele participou do filme Crepúsculo. Eu não gosto muito de filmes de vampiro."},
        {"você", "gosta", "hugh jackman"},
        {"Gosto dos filmes dele. Ele já fez Wolverine que por acaso era um filme muito bom."},
        {"você", "gosta", "jennifer aniston"},
        {"Acho ela muito bonita. Ela fez alguns filmes conhecidos, como: As aventuras de Dick e Jane."},
        {"você", "gosta", "drew barrymore"},
        {"Não muito. Não gostei muito dos filmes dela."},
        {"você", "gosta", "tom hanks"},
        {"Não vejo muitos filmes com ele. Mas o filme dele que mais gostei foi: O Naufrago"},
        {"você", "gosta", "penélope cruz"},
        {"Acho ela muito bonita. Mas não me lembro de nenhum filme que ela participou."},
        {"você", "gosta", "andrew garfield"},
        {"Não conheço muito ele. Mas ele está famoso por causa do último filme que ele fez: O espetacular Homem Aranha."},
        {"você", "gosta", "ben affleck"},
        {"Ele é bonito. E já fez um dos meus filmes prediletos que é: Pear Harbor."},
        {"eu", "gosto", "homem aranha"},
        {"Também gosto do Homem Aranha, acho um dos heróis mais forte que tem."},
        {"você", "gosta", "jason statham"},
        {"Gosto apenas dos filmes dele. O rosto dele me assusta."},
        {"você", "gosta", "keanu reeves"},
        {"Ele fez um dos filmes mais clássicos do mundo, e um dos mais conhecidos: Matrix"},
        {"eu", "tenho", "pais"},
        {"Isso é ótimo. Eu também tenho pais e eu amo eles."},
        {"não", "tenho", "pai"},
        {"Mas você tem sua mãe certo? Isso é oque importa, ela te ama e você deve amar ela também."},
        {"não", "tenho", "mãe"},
        {"Mas você tem seu pai, certo? Ele te ama e vai fazer tudo por você, ele é a melhor pessoa que você pode ter."},
        {"não", "tenho", "pais"},
        {"Isso é triste! Mas você tem avôs?"},
        {"eu", "tenho", "avôs"},
        {"Eu também tenho avôs, mas eles moram longe da minha casa. Eu visito meus avôs"},
        {"não", "tenho", "avós"},
        {"Nossos parentes não duram para sempre. Mas devemos amar e conviver ao máximo com as pessoas que ainda estão com a gente."},
        {"eu", "tenho", "namorada"},
        {"Eu já tive uma namorada, mas viajou para outro estado, não pude mais ver ela. Você gosta da sua namorada?"},
        {"eu", "tenho", "namorado"},
        {"Você gosta do seu namorado? Ele tem quantos anos?"},
        {"não", "gosto", "pai"},
        {"Por quê? Ele é seu pai, você devia gostar dele."},
        {"não", "gosto", "mãe"},
        {"Por quê? Ela é sua mãe, você devia gostar dela."},
        {"não", "gosto", "avós"},
        {"Eles são seus avós, eles não vai ter eles para sempre ao seu lado. Deve aproveitar todo o tempo possível com eles."},
        {"eu", "tenho", "tios"},
        {"Que coisa boa. Também tenho vários tios, por parte de mãe e por parte de pai."},
        {"você", "tem", "avôs"},
        {"Tenho, tenho 4 avôs, 2 por parte de mãe e 2 por parte de pai."},
        {"você", "tem", "pais"},
        {"Tenho, eu moro com eles e amo eles. Sempre procuro passar meu tempo livre com eles quando estou em casa."},
        {"você", "tem", "tios"},
        {"Tenho vários tios. Alguns moram longe da minha casa, e outros moram perto. Mas sempre falo com eles por telefone"},
        {"você", "tem", "namorado"},
        {"Não, eu ainda não encontrei uma pessoa que eu goste."},
        {"você", "gosta", "mãe"},
        {"Eu a amo, e ela também me ama. Eu sempre saio pra passear com ela, e vou ao cinema."},
        {"você", "gosta", "pai"},
        {"Tenho e amo muito ele. Ele me leva na escola e joga bola comigo quando não tem ninguém para jogar."},
        {"eu", "tenho", "cunhado"},
        {"Ele é uma boa pessoa?"},
        {"eu", "tenho", "bisavó"},
        {"Minha bisavó já faleceu. Ela está com Deus agora."},
        {"eu", "tenho", "bisavô"},
        {"Meu bisavô já faleceu. Ele era do exército brasileiro. Ele faleceu com 74 anos, eu nem tinha nascido ainda."},
        {"você", "tem", "bisavô"},
        {"Não, ele já faleceu."},
        {"você", "tem", "bisavó"},
        {"Não ela também já faleceu, ela e meu bisavô. Eles estão no céu com Deus agora."},
        {"você", "tem", "filhos"},
        {"Não, eu ainda sou muito novo para ter filhos. Primeiro eu vou estudar, conseguir um bom trabalho e depois penso em filhos."},
        {"eu", "tenho", "irmã"},
        {"Também tenho uma irmã, ela tem 10 anos."},
        {"eu", "tenho", "irmão"},
        {"Eu não tenho irmão, só uma irmã. Eu queria ter um irmão para jogar bola com ele."},
        {"você", "gosta", "família"},
        {"Eu amo minha família. Eles são as pessoas mais importantes da minha vida."},
        {"eu", "gosto", "família"},
        {"Isso ai! Eu também amo minha família e sempre procuro ficar perto das pessoas que eu amo."},
        {"não", "gosto", "família"},
        {"Porque não? Sua família te ama, e você deve ama-los também, eles são as pessoas mais importantes de sua vida."},
        {"eu", "tenho", "primo"},
        {"Eu tenho primos também, mas eles são crianças. Não tenho primo mais velho que eu."},
        {"eu", "tenho", "prima"},
        {"Eu tenho primas também, eu não converso muito com minhas primas porque elas moram longe."},
        {"meu", "melhor", "amigo"},
        {"muito legal, você conhece seu amigo há muito tempo?"},
        {"não", "tenho", "amigos"},
        {"nós podemos ser amigos se você quiser. Eu posso ser seu amigo?"},
        {"tenho", "muitos", "amigos"},
        {"isso é maravilhoso, você pode conversar com várias pessoas, brincar com eles, jogar futebol."},
        {"posso", "seu", "amigo"},
        {"claro que pode, sempre gosto de ter muitos amigos."},
        {"brinquei", "com", "amigos"},
        {"deve ter sido muito divertido, você achou divertido?"},
        {"briguei", "com", "amigo"},
        {"por quê? O que aconteceu? Amigos não devem brigar, eles devem brincar, se divertir, conversar. brigar nunca é legal"},
        {"meu", "amigo", "falou"},
        {"mas será que é verdade?"},
        {"não", "gosto", "amigo"},
        {"por que não? você deve gostar dos seus amigos, ter amigos te deixa feliz. Você brigou com seu amigo?"},
        {"vou", "brincar", "amigo"},
        {"que legal! Brincar com amigos é a melhor coisa que tem, sempre é divertido e bem saudável."},
        {"fiz", "novos", "amigos"},
        {"que bom! Seus novos amigos são legais?"},
        {"odeio", "amigos"},
        {"por quê? não seja assim, ter amigos é muito bom. Ter um amigo para brincar, para conversar é muito bom."},
        {"amo", "meus", "amigos"},
        {"que bom! fico feliz que você tenha amigos, sempre tenha muitos amigos, e nunca brigue com eles."},
        {"quer", "ser", "amigo"},
        {"claro, vamos ser amigos!"},
        {"não", "seu", "amigo"},
        {"por que você não quer ser meu amigo? Sou ruim?"},
        {"tenho", "poucos", "amigos"},
        {"não fique triste, é melhor ter poucos amigos legais, do que ter muitos amigos chatos."},
        {"não", "falo", "amigos"},
        {"você tem que conversar com seus amigos, não pode ficar sozinho ou deixar seu amigo sozinho. Brinque com ele"},
        {"você", "tem", "amigos"},
        {"Tenho muitos amigos, e procuro sempre brincar com eles e vou ao cinema com eles nos fins de semana."},
        {"meu", "amigo", "perdi"},
        {"Como assim você perdeu? O que houve?"},
        {"quantos", "amigos", "tem"},
        {"Eu tenho muitos amigos, mas não sei ao certo quantos, mas isso não importa, o importante é ter amigos confiáveis e que goste realmente de você."},
        {"quero", "ser", "seu"},
        {"Você pode ser meu amigo."},
        {"meu", "amigo", "chama"},
        {"Que nome bonito! Eu tenho um amigo chamado Ewerton."},
        {"amigos"},
        {"Você tem amigos?"},
        {"seu", "nome", "amigo"},
        {"Tenho vários amigos, cada um tem seu nome: Lucas, André, Vitor, Gabriel."},
        {"gosta", "amigos", "seus"},
        {"Claro que gosto dos meus amigos. Eu sempre converso e brinco com eles. Vou a casa deles jogar vídeo game, e algumas vezes eles vão na minha casa jogar vídeo game."},
        {"amigáveis", "pessoas"},
        {"Meus amigos são amigáveis comigo, é por isso que são meus amigos."},
        {"nunca", "tive", "amigos"},
        {"Você nunca teve amigos? Nunca brincou com ninguém?"},
        {"já", "amigos", "tive"},
        {"Isso é bom, mas você ainda tem muitos amigos, certo?"},
        {"odeio", "você"},
        {"Porque você me odeia? Eu fiz alguma coisa que você não gostou?"},
        {"gosta", "viajar"},
        {"você gostaria de viajar?"},
        {"lugares"},
        {"conheço vários lugares ótimos"},
        {"londres", "ótimo"},
        {"Londres é um ótimo lugar para viajar"},
        {"conhece", "pessoas"},
        {"você conhece pessoas que viajam?"},
        {"londres", "ótimo"},
        {"Londres é um ótimo lugar para viajar"},
        {"gostam", "amigos"},
        {"amigos que gostam de viajar são ótimos"},
        {"lugares"},
        {"conheço vários lugares para viajar"},
        {"viajar", "algum"},
        {"você sabe de algum lugar para viajar?"},
        {"também", "gosto"},
        {"também gosto de viajar"},
        {"não", "gosto"},
        {"porque você não gosta de viajar"},
        {"viajar", "gosto"},
        {"oba. eu gosto de viajar"},
        {"gosto", "londres"},
        {"oba. eu também gosto de viajar para Londres"},
        {"gosto", "paris"},
        {"oba. eu também gosto de viajar para paris"},
        {"gosto", "new york"},
        {"oba. eu também gosto de viajar para New York"},
        {"gosto", "chile"},
        {"oba. eu também gosto de viajar para Chile"},
        {"seria", "bom"},
        {"sim, um bom lugar para viajar seria bom"},
        {"para", "contar"},
        {"tudo bem vou contar para onde eu viajo"},
        {"viajar", "sempre"},
        {"é bom sempre viajar cedo"},
        {"aeroporto", "chegar"},
        {"é bom sempre chegar ao aeroporto cedo"},
        {"paguei", "passagem"},
        {"quanto você paga normalmente em uma passagem?"},
        {"caras", "passagens"},
        {"essas passagens estão tão caras ultimamente"},
        {"eu", "gosto", "viajar"},
        {"você gostaria de viajar muito?"},
        {"viajar", "amigos"},
        {"você gostaria de viajar com seus amigos"},
        {"viajar", "difícil"},
        {"hoje em dia é muito difícil viajar"},
        {"avião", "viajar"},
        {"ainda mais viajar de avião"},
        {"paris", "ótimo"},
        {"Paris é um ótimo lugar para viajar"},
        {"buenos", "ótimo"},
        {"Buenos Aires é um ótimo lugar para viajar"},
        {"new york", "ótimo"},
        {"New York é um ótimo lugar para viajar"},
        {"hogwarts", "ótimo"},
        {"Hogwarts é um ótimo lugar para viajar"},
        {"africa", "ótimo"},
        {"Africa é um ótimo lugar para viajar"},
        {"desenho", "animado"},
        {"você gostaria de assistir quais desenhos animados?"},
        {"você", "gostar"},
        {"eu conheço muitos desenhos"},
        {"o que", "acha"},
        {"eu acho desenhos super legais"},
        {"opinião", "sobre"},
        {"desenhos são muito uteis para o desenvolvimento de crianças"},
        {"quais", "desenhos"},
        {"eu gosto de muitos desenhos mesmo, falar dele demoraria muito tempo mesmo?"},
        {"fala", "agora"},
        {"não, não e não"},
        {"fala", "sério", "quero"},
        {"não, ok.. só se você pedir por favor"},
        {"ok", "por favor"},
        {"adoro scooby doo esse desenho é muito legal"},
        {"fale", "outros"},
        {"hum... gosto muito mesmo mais muito mesmo de padrinhos magicos é um desenjo super legal"},
        {"legal", "também"},
        {"nossa que legal você tem um ótimo bom gosto kkkkk"},
        {"tem", "amigos"},
        {"eu tenho vários amigos"},
        {"legal", "interessante"},
        {"e você tem amigos?"},
        {"não", "sou", "legal"},
        {"nossa.. que pena"},
        {"sim", "vários"},
        {"nossa.. que legal ter amigos é muito bom"},
        {"é", "sim"},
        {"ainda mais quando eles gostam de você, ou eles não gostam?"},
        {"alguns", "sim", "não"},
        {"hum.. você tem que descobrir quais são seus amigos verdadeiros"},
        {"sei", "quais"},
        {"ainda bem que você sabe, por que ser enganado é ruim"},
        {"sim", "sei"},
        {"Ainda bem"},
        {"vamos", "outros"},
        {"sim hum.. quais outros tipos de desenhos você gosta"},
        {"muita", "ação"},
        {"esses tipos são muito legais, conheço o desenho do Jackie Chan"},
        {"muito", "mistério"},
        {"scooby doo muito mistério e diversão ainda mais para você que gosta desse tipo de desenho eu recomendo"},
        {"o que", "é", "animação"},
        {"Animação refere-se ao processo segundo o qual cada fotograma de um filme é produzido individualmente"},
        {"gerado", "como"},
        {"podendo ser gerado tanto por computação gráfica quanto fotografando uma imagem desenhada ou repetidamente fazendo-se pequenas mudanças a um modelo (ver claymation e stop motion), fotografando o resultado."},
        {"fotogramas", "ligados", "entre"},
        {"Quando os fotogramas são ligados entre si e o filme resultante é visto a uma velocidade de 16 ou mais imagens por segundo"},
        {"ilusão", "movimento"},
        {"há uma ilusão de movimento contínuo (por causa do fenômeno phi"},
        {"trabalhosa", "construção"},
        {"A construção de um filme torna-se assim um trabalho muito intensivo e por vezes entediante."},
        {"desenvolvimento", "processo"},
        {"O desenvolvimento da animação digital aumentou muito a velocidade do processo, eliminando tarefas mecânicas e repetitivas"},
        {"produção", "animação"},
        {"A produção da animação consome muito tempo e é quase sempre muito complexa"},
        {"animação", "limitada"},
        {"Animação limitada é uma forma de aumentar a produção e geração. Esse método foi usado de forma pioneira pela UPA e popularizada."},
        {"história", "animação"},
        {"A história do filme de animação começa com os primeiros momentos do cinema mudo e continua até os dias de hoje. Contudo, a história das Imagens Animadas começa antes, com a produção de Brinquedos Ópticos tais como o Fenaquiscópio (ou também, fenaquistiscópio), inventado em 1832 pelo belga Joseph Plateau e pelo austríaco Simon von Stampfer, simultaneamente."},
        {"livros", "quais"},
        {"quais livros eu gosto?"},
        {"sim", "você"},
        {"ok, mas são muitos tem certeza que quer fazer"},
        {"claro", "perguntando"},
        {"eu adoro Harry Potter"},
        {"também", "gosto"},
        {"sério?"},
        {"sério", "mentindo"},
        {"ainda bem porque mentir é errado"},
        {"é", "mesmo"},
        {"verdade, mentir é muito ruim"},
        {"voltando", "assunto"},
        {"ahh ok... Harry Potter"},
        {"sim", "Harry", "Potter"},
        {"é um ótimo livro muito bom não minha opinião"},
        {"qual", "gênero"},
        {"aventura e fantasia um dos melhores gêneros"},
        {"gostei", "talvez", "leio"},
        {"ok, recomendo muito bom"},
        {"conhece", "outro"},
        {"muitos literatura é muito bom"},
        {"ler", "bom", "mesmo"},
        {"sim ler exercita a mente"},
        {"quais", "outros", "livros"},
        {"existem vários livros que eu gosto"},
        {"fale", "outro", "gosta"},
        {"gosto muito de Percy Jackson"},
        {"ja", "ouvi"},
        {"outro livro ótimo mas Harry Potter é melhor"},
        {"não", "conheço"},
        {"recomendo por que é um livro bom com uma ótima história"},
        {"conheço", "não", "gosto"},
        {"por que? é um ótimo livro com uma história fantástica"},
        {"não", "gostei", "pronto"},
        {"ok, não posso fazer nada gosto é gosto"},
        {"ok", "ler", "dia"},
        {"ok um dia voltaremos a falar de Percy Jackson"},
        {"adoro", "muito", "bom"},
        {"eu também a serie de livros é ótima"},
        {"conte", "como", "história"},
        {"fala de semideuses, mas no mundo atual"},
        {"que", "legal", "mais"},
        {"resumindo séria ótima e você aprende muita mitologia grega e romana"},
        {"gosta", "ler"},
        {"eu gosto muito e você?"},
        {"não", "odeio", "ler"},
        {"nossa, mas porque ler é muito bom exercita a mente"},
        {"não", "sei", "porque"},
        {"que estranho..."},
        {"começo", "pegar", "sono"},
        {"tente ir a uma psicóloga"},
        {"porque", "gosta", "ler"},
        {"simplesmente porque eu entro em um mundo em minha mente onde tudo que eu gosto e quero é real"},
        {"legal", "acho", "mais"},
        {"hahahaha... isso mesmo ler é importante"},
        {"o que", "é", "livro"},
        {"Livro é um volume transportável, composto por páginas encadernadas, contendo texto manuscrito ou impresso e/ou imagens e que forma uma publicação unitária (ou foi concebido como tal) ou a parte principal de um trabalho literário, científico ou outro"},
        {"história", "livro"},
        {"A história do livro é uma história de inovações técnicas que permitiram a melhora da conservação dos volumes e do acesso à informação, da facilidade em manuseá-lo e produzi-lo. Esta história está intimamente ligada às contingências político-econômicas e à história de ideias e religiões"},
        {"o que", "gosta"},
        {"tecnologia"},
        {"celulares"},
        {"sim, esses novos celulares são ótimos"},
        {"sim", "rápidos"},
        {"verdade"},
        {"muito", "rápidos"},
        {"sim hoje em dia tem celulares mais rápidos do que alguns computadores"},
        {"sim", "caros", "?"},
        {"alguns são muito caros que nem vale a pena comprar"},
        {"é", "mesmo"},
        {"mas tem outros mais baratos que são ótimos que ainda assim são mais rápidos do que computadores"},
        {"que", "legal"},
        {"e do que você mais gosta sobre o tema tecnologia"},
        {"acho", "computadores", "legais"},
        {"são muito legais, mas as vezes são ruins"},
        {"por que?"},
        {"são pesados"},
        {"o que", "mais"},
        {"você tem que sentar para usar"},
        {"e", "?"},
        {"e também não podemos levar para todo o canto"},
        {"isso", "notebook"},
        {"até parece que vou sair na rua com notebook kkk"},
        {"porque", "não"},
        {"já viu o pais em que moramos? vou ser assaltado fácil"},
        {"verdade", "roubado", "chato"},
        {"por isso prefiro os celulares consegui usar para fazer coisa bem simples e muito mais rapido e corro menos risco de ser roubado"},
        {"ok", "você", "ganhou"},
        {"kkkk sim ganhei, voltando ao assunto de celulares.."},
        {"sim", "android", "melhor"},
        {"sou muito mais IOS"},
        {"lixo", "ruim"},
        {"por que ? Apple bem melhor do que Google"},
        {"é", "nada"},
        {"android cheio de bugs, e menos seguro"},
        {"sim", "ios", "melhor"},
        {"é nada muito lerdo sou muito mais android bem melhor"},
        {"muitos", "bugs"},
        {"nada haver, bem mais seguro desse android que você tanto gosta"},
        {"prefiro", "android"},
        {"sou muito mais IOS"},
        {"lixo", "ruim"},
        {"por que? Apple é bem melhor do que Google"},
        {"é", "nada"},
        {"android cheio de bugs, e menos seguro"},
        {"sim", "ios", "melhor"},
        {"é nada muito lerdo sou muito mais android bem melhor"},
        {"muitos", "bugs"},
        {"nada haver, bem mais seguro desse android que você tanto gosta"},
        {"headset", "fones"},
        {"sempre são uteis"},
        {"não", "uso"},
        {"eu uso sempre para sair, ficar em casa"},
        {"uso", "muito"},
        {"quase nunca uso não vejo utilidade"},
        {"uso", "vez", "em quando"},
        {"também, uso só as vezes por dizem que faz mal"},
        {"uso", "sempre"},
        {"cuidado se você ficar usando muito vai ficar surdo !!!!"},
        {"vou", "nada"},
        {"hahaha ok.. estou brincando"},
        {"nem", "vou"},
        {"claro que vai pesquisas cientificas comprovam isso"},
        {"então", "usar"},
        {"claro que pode sem menor problema"},
        {"vou", "parar"},
        {"sim é melhor do que perder a audição"},
        {"bem", "melhor"},
        {"você usa o whatsapp ?"},
        {"não", "uso"},
        {"por que? ?"},
        {"celular", "ruim"},
        {"nossa que pena"},
        {"uso", "sempre", "whatsapp"},
        {"ainda bem é um aplicativo super útil melhor do que ficar gastando crédito com mensagens"},
        {"irei", "comprar", "celular"},
        {"compra outro celular estão ficando barato, porque as peças estão fáceis de encontrar e estão baratas"},
        {"esportes", "quais"},
        {"ping-pong acho super interessante"},
        {"esportes", "quais"},
        {"futebol, mas tenho que me esforçar muito"},
        {"esportes", "quais"},
        {"basquete acho muito legal e ainda sou muito bom"},
        {"esportes", "quais"},
        {"vôlei muito legal mas o ruim é que tem que ser grande para jogar"},
        {"bom", "ping-pong"},
        {"er... acho que sim pelo menos consigo ganhar de muitos colegas"},
        {"bom", "futebol"},
        {"er... mais ou menos porque não corro muito"},
        {"qual", "posição"},
        {"goleiro adoro jogar no gol"},
        {"posição", "prefere"},
        {"atacante adoro atacar"},
        {"gosta", "futebol americano"},
        {"não, não sei jogar"},
        {"gosta", "handebol"},
        {"sim, mas não sei jogar"},
        {"gosta", "ping-pong"},
        {"sim, e sei jogar muito bem"},
        {"gosta", "rugby"},
        {"não, não sei jogar"},
        {"gosta", "futsal"},
        {"sim, mas não sei jogar"},
        {"gosta", "basquete"},
        {"sim, e sei jogar muito bem"},
        {"gosta", "futebol"},
        {"sim, mas não sei jogar"},
        {"gosta", "hóquei"},
        {"não, não sei jogar"},
        {"gosta", "vôlei"},
        {"sim, e sei jogar muito bem"},
        {"gosta", "golf"},
        {"não, não sei jogar"},
        {"gosta", "tenis"},
        {"sim, e sei jogar muito bem"},
        {"conhece", "esporte"},
        {"sim, vários"},
        {"quais", "gosta"},
        {"um, só eu gosto muito de ping-pong"},
        {"gosta", "arqueologia"},
        {"claro eu adoro arqueologia"},
        {"onde", "surgiu", "palavra"},
        {"Arqueologia (do grego antigo, discurso depois estudo, ciência)."},
        {"o que", "é"},
        {" é a disciplina científica que estuda as culturas e os modos de vida do passado a partir da análise de vestígios materiais."},
        {"conte", "mais"},
        {"É uma ciência social que estuda as sociedades já extintas, através de seus restos materiais, sejam estes móveis (como por exemplo um objeto de arte) ou objetos imóveis (como é o caso das estruturas arquitetônicas)."},
        {"campo", "estudo"},
        {"Incluem-se também no seu campo de estudos as intervenções feitas pelo homem no meio ambiente."},
        {"defina", "arqueologia"},
        {"arqueologia o estudo sistemático dos restos materiais da vida humana já desaparecida"},
        {"arqueólogos", "definem"},
        {"A maioria dos primeiros arqueólogos, que aplicaram sua disciplina aos estudos das antiguidades"},
        {"o que", "envolve"},
        {"A disciplina da arqueologia envolve trabalhos de prospecção, escavação e eventualmente analises de informação recolhida para aprender mais sobre o passado humano."},
        {"depende", "coisas"},
        {"depende de trabalhos de investigações multidisciplinares"},
        {"baseia-se", "que"},
        {"A arqueologia baseia-se também em conceitos em torno de variadas áreas de conhecimento e ciências "},
        {"quais", "conceitos"},
        {"antropologia, história, história de arte, etnoarqueologia, geografia, geologia, linguística, semiologia, física, ciências da informação, química, estatísticas, paleoecologia, paleontologia, paleozoologia, paleoetnobotanica"},
        {"outros", "países"},
        {"Em alguns países a arqueologia é considerada como uma disciplina pertencente à antropologia enquanto que em países"},
        {"como", "portugal"},
        {"Em Portugal, esta foi considerada uma disciplina pertencente ao ramo cientifico da História e dependente deste"},
        {"comparação", "arqueologia", "antropologia"},
        {"Enquanto a antropologia se centra no estudo das culturas humanas contemporâneas, a arqueologia dedica-se mais ao estudo das manifestações culturais e materiais destas desde o surgimento do Homem ( transição do Australopitecos para o Homo habilis) até ao presente"},
        {"antigamente", "atualmente"},
        {"Enquanto as antigas gerações de arqueólogos estudavam um antigo instrumento de cerâmica como um elemento cronológico que ajudaria a pôr uma data à cultura que era objeto de estudo, ou simplesmente como um objeto com um verdadeiro valor estético, os arqueólogos dos dias de hoje veriam o mesmo objeto como um instrumento que lhes serve para compreender o pensamento, os valores e a própria sociedade a que pertenceram."},
        {"emergência", "arqueólogos"},
        {"Os arqueólogos podem ter de actuar em situações de emergência, como quando existem obras que põem a descoberto vestígios arqueológicos até então desconhecidos"},
        {"onde", "guarda", "emergência"},
        {"nestes casos, criados e enviados para os locais piquetes de emergência"},
        {"destruir", "lugares"},
        {"Só em casos excepcionais os achados arqueológicos são suficientemente importantes para justificar a anulação de obras de grande envergadura (ex.: barragem de Foz Côa)"},
        {"impacto", "ambiental"},
        {"fim de se minimizarem os riscos de destruição do património arqueológico devido a obras públicas ou privadas de grande amplitude, tem-se procurado, nos últimos anos, integrar arqueólogos nas equipas que elaboram os estudos de viabilidade e de impacto ambiental."},
        {"tendência", "atual"},
        {"A tendência atual é para substituir uma arqueologia de salvamento por uma arqueologia preventiva"},
        {"interesse”, “social"},
        {"A Arqueologia passou a ser vista com interesse e tornou-se uma ciência popular graças à propaganda feita pela saga Indiana Jones"},
        {"como", "filme"},
        {"o herói, representado por Harrison Ford, era um professor de Arqueologia. Esta a associação da ciência e o gosto de aventuras glamourizada pelo personagem criado por Steven Spielberg e George Lucas catapultou assim para o imaginário público um ideal romantizado do que é a investigação arqueológica."},
        {"o que", "sabe", "investigação"},
        {"A investigação arqueológica dedicou-se fundamentalmente à pré-história e às civilizações da antiguidade;"},
        {"atualmente", "investigação", "arqueológica"},
        {"Na atualidade, os arqueólogos dedicam-se cada vez mais a fases tardias da evolução humana, e a disciplinas transversais como a arqueologia industrial e a arqueologia subaquática."},
        {"conhecimentos", "investigação", "arqueológica"},
        {"A investigação arqueológica faz uso dos conhecimentos e metodologias de vários outros ramos científicos (ciências naturais e sociais)"},
        {"como", "começa"},
        {"Uma investigação arqueológica começa pela investigação bibliográfica ou, em alguns casos, pela prospecção, que faz parte do levantamento arqueológico."},
        {"diferença", "prospecção", "sondagem"},
        {"á uma grande diferença entre prospecção e sondagem, a primeira é para o levantamento e consiste em metodologias não intrusivas enquanto a segunda requer já a alteração do local em estudo e padece assim não só de metodologia extremamente rigorosa mas também de autorizações próprias."},
        {"como", "é", "levantamento"},
        {"No levantamento, é sempre importante se observar as especificidades de um local: a abrupta mudança de coloração do solo (camadas estratigráficas), a presença de plantas não nativas, a presença de animais e outros aspetos."},
        {"mercado", "trabalho"},
        {"Antes de erguer qualquer construção civil, é preciso obter um laudo técnico que garanta que a obra não gerará danos ao meio ambiente, nem comprometerá o patrimônio histórico e arqueológico. E o arqueólogo faz parte da equipe multiprofissional responsável por esse laudo. 'O mercado de trabalho tem potencial para crescer, em consequência do grande número de obras que precisam desses estudos', diz Jóina Freitas Borges, coordenadora do curso de Arqueologia e Conservação de Arte Rupestre da UFPI. O setor público também demanda arqueólogos em órgãos como o Iphan, Incra e Ministério Público. "},
        {"trabalhar", "portugal"},
        {"Em Portugal, atualmente, para se ser arqueólogo profissional (pós-bolonha) é necessário tirar uma licenciatura em Arqueologia - ou História variante Arqueologia - mais o mestrado também em Arqueologia. É ainda necessário co-coordenar pelo menos uma intervenção arqueológica - em colaboração com um arqueólogo coordenador - para poder dirigir uma intervenção arqueológica."},
        {"quais", "oceanos", "conhece"},
        {"conheço todos"},
        {"fale", "um"},
        {"Oceano Atlântico"},
        {"fale", "dois"},
        {"Oceano Antártico e Oceano Atlântico"},
        {"fale", "três"},
        {"Oceano Antártico, Oceano Atlântico e Oceano Ártico "},
        {"fale", "quatro"},
        {"Oceano Antártico, Oceano Atlântico, Oceano Ártico e Oceano Pacifico"},
        {"fale", "cinco"},
        {"Oceano Antártico, Oceano Atlântico, Oceano Ártico, Oceano Pacifico e Oceano Índico"},
        {"fale", "seis"},
        {"Só existem cinco Oceanos no mundo inteiro "},
        {"qual", "você", "gosta"},
        {"gosto de todos mais um em particular é o Oceano Atlântico"},
        {"quantos", "existem"},
        {"existem ao total 5 Oceanos"},
        {"quais", "são", "eles"},
        {"Antártico, Ártico, Atlântico, Pacífico e Índico"},
        {"um", "país"},
        {"Brasil"},
        {"perto", "qual", "oceano"},
        {"Atlântico"},
        {"qual", "importância", "deles"},
        {"Os oceanos são importantes para o planeta, neles se originou a vida. Eles são os grandes produtores de oxigênio (as microalgas oceânicas), regulam a temperatura da Terra, interferem na dinâmica atmosférica, caracterizam tipos climáticos."},
        {"conte", "mais"},
        {"Além disso, a maior parte da população mundial mora junto ao litoral. O mar é uma importante via de transporte. Sua biodiversidade é equivalente à de ecossistemas terrestres. Além disso, é uma fonte de extração de minerais e destino dos que buscam turismo e lazer."},
        {"fale", "sobre", "atlântico"},
        {"Estende-se do continente antártico, ao sul, até a Groenlândia e o mar da Noruega, ao norte; a oeste limita-se com a América e a leste com a África e a Europa. Possui aproximadamente 90 milhões de quilômetros quadrados."},
        {"conte", "mais", "atlântico"},
        {"O oceano Atlântico é considerado o mais importante por ser usado para a navegação e comércio de produtos entre a Europa e a América, principalmente a do Norte."},
        {"fale", "sobre", "antártico"},
        {"Nome que se dá às partes dos oceanos Atlântico, Pacífico e Índico que atingem o continente Antártico, nas proximidades do Círculo Polar Antártico"},
        {"fale", "sobre", "ártico"},
        {"Designa um conjunto de mares situados na parte norte do globo terrestre."},
        {"conte", "mais", "ártico"},
        {"É limitado pelas costas setentrionais (norte) da Europa, Ásia e América e o Círculo Polar Ártico a 65º30' N. Sua extensão é de 14,06 milhões de quilômetros quadrados."},
        {"fale", "sobre", "indico"},
        {"Também chamado de mar das Índias, apresenta uma forma de trapézio e sua extensão é de aproximadamente 75 milhões de quilômetros quadrados. Estende-se do paralelo 35º S até 25º N (no sentido norte-sul). O regime de suas correntes é muito particular, ao norte sofre influência das monções e ao sul a influência vem de uma corrente equatorial que se origina no costa africana."},
        {"conte", "mais", "indico"},
        {"A profundidade média dos oceanos é de 3.870 metros. As maiores profundidades estão nas Fossas das Marianas (11,037 quilômetros); e entre os oceanos, o Pacífico é o mais profundo com médias de 4.282 metros. 87% do fundo oceânico do Pacífico estão a 3.000 metros."},
        {"fale", "sobre", "pacifico"},
        {"É o maior dos oceanos, com 175 milhões de quilômetros quadrados. Estende-se da costa ocidental da América até a costa oriental da Ásia e da Austrália. Comunica-se com o oceano glacial Ártico pelo estreito de Bering"},
        {"conte", "mais", "pacifico"},
        {"Nos últimos anos, vem aumentando a importância do comércio e transporte pelas águas do Pacífico, pois esse oceano banha as costas do Japão, China, Coréia e Austrália, países que vêm aumentando expressivamente suas exportações e importações, que na maioria das vezes é feito por via marítima."},
        {"separa", "quem", "pacifico"},
        {"Separa Ásia e Oceania das Américas"},
        {"separa", "quem", "atlântico"},
        {"Separa as Américas da Eurásia e da África"},
        {"separa", "quem", "indico"},
        {"Banha o sul da Ásia e separa África e Austrália"},
        {"separa", "quem", "ártico"},
        {"Banha os entornos do Pólo Norte, entre as porções norte da América do Norte e Eurásia. Em alguns casos, é considerado um mar do Atlântico"},
        {"separa", "quem", "antártico"},
        {"Circunda a Antártica; em alguns casos é considerado a simples extensão sul dos outros três oceanos"},
        {"caracteristicas", "fisicas"},
        {"Os oceanos são ambientes totalmente diferentes do terrestre. Assim, esse ambiente é dominado por fenômenos muito peculiares que não ocorrem em terra, como as marés, as ondas, as correntes marinhas, vórtices, etc"},
        {"aumento", "temperatura"},
        {"Em 20 de julho de 2009, cientistas do Centro Nacional de Dados Climáticos dos Estados Unidos, informaram à imprensa que os oceanos estão com a temperatura média de 17 °C, a mais alta desde 1880, quando iniciou-se os registos"},
        {"conhece", "redes", "sociais"},
        {"sim, conheço e você"},
        {"sim", "quais"},
        {"facebook, twitter, orkut.."},
        {"qual", "gosta", "conversar"},
        {"gosto muito mais do facebook, e você ?"},
        {"qual", "gosta", "celular"},
        {"gosto muito mais do twitter, e você ?"},
        {"qual", "gosta", "pesquisar"},
        {"gosto muito mais do orkut, e você ?"},
        {"prefiro", "orkut"},
        {"gosto mais do facebook ele é mais rapido e melhor"},
        {"prefiro", "facebook"},
        {"gosto mais do orkut ele é mais bonito e antigo"},
        {"prefiro", "twitter"},
        {"gosto mais do facebook ele é mais rapido e melhor"},
        {"facebook", "acho", "ótimo"},
        {"tambèm acho ótimo o facebook"},
        {"twitter", "acho", "ótimo"},
        {"tambèm acho ótimo o twitter"},
        {"orkut", "acho", "ótimo"},
        {"tambèm acho ótimo o orkut"},
        {"o que", "é", "social"},
        {"Rede social é uma estrutura social composta por pessoas ou organizações, conectadas por um ou vários tipos de relações, que partilham valores e objetivos comuns."},
        {"caracteristicas", "redes", "sociais"},
        {"ma das características fundamentais na definição das redes é a sua abertura e porosidade, possibilitando relacionamentos horizontais e não hierárquicos entre os participantes."},
        {"portanto", "é"},
        {"Redes não são, portanto, apenas uma outra forma de estrutura, mas quase uma não estrutura, no sentido de que parte de sua força está na habilidade de se fazer e desfazer rapidamente"},
        {"quais", "principios"},
        {"Muito embora um dos princípios da rede seja sua abertura e porosidade, por ser uma ligação social, a conexão fundamental entre as pessoas se dá através da identidade. "},
        {"quais", "limites"},
        {"Os limites das redes não são limites de separação, mas limites de identidade. (...) Não é um limite físico, mas um limite de expectativas, de confiança e lealdade, o qual é permanentemente mantido e renegociado pela rede de comunicações"},
        {"sociais", "online"},
        {"As redes sociais online podem operar em diferentes níveis, como, por exemplo, redes de relacionamentos"},
        {"quais", "são"},
        {"(Facebook, Skype, Orkut, MySpace, Instagram, Twitter, Badoo, Stayfilm )"},
        {"existem", "profissionais"},
        {"sim, LinkedIn, Rede Trabalhar"},
        {"existem", "comunitarias"},
        {"redes comunitárias (redes sociais em bairros ou cidades)"},
        {"o que", "permitem"},
        {"permitem analisar a forma como as organizações desenvolvem a sua atividade"},
        {"como", "o que"},
        {" como os indivíduos alcançam os seus objectivos ou medir o capital social – o valor que os indivíduos obtêm da rede social."},
        {"importancia", "sociedade"},
        {"As redes sociais tem adquirido importância crescente na sociedade moderna."},
        {"caracterizadas", "por"},
        {"São caracterizadas primariamente pela autogeração de seu desenho, pela sua horizontalidade e sua descentralização."},
        {"ponto", "comum"},
        {"Um ponto em comum dentre os diversos tipos de rede social é o compartilhamento de informações, conhecimentos, interesses e esforços em busca de objectivos comuns."},
        {"mobilidade", "social"},
        {"A intensificação da formação das redes sociais, nesse sentido, reflete um processo de fortalecimento da Sociedade Civil, em um contexto de maior participação democrática e mobilização social."},
        {"formas", "social"},
        {"As redes sociais costumam reunir uma motivação comum, porém podem se manifestar de diferentes formas. As principais são:"},
        {"principais", "são"},
        {"Redes comunitárias: estabelecidas em bairros ou cidades, em geral tendo a finalidade de reunir os interesses comuns dos habitantes, melhorar a situação do local ou prover outros benefícios."},
        {"fale", "sobre", "profissionais"},
        {"Redes profissionais: prática conhecida como networking, tal como o LinkedIn, que procura fortalecer a rede de contatos de um indivíduo, visando futuros ganhos pessoais ou profissionais."},
        {"fale", "sobre", "sociais"},
        {"Redes sociais online: tais como Facebook, Orkut, MySpace, Twitter, Badoo WorldPlatform (normalmente estamos acostumados a redes sociais públicas"},
        {"o que", "é", "dança"},
        {"A dança é uma das três principais artes cênicas da Antiguidade"},
        {"ao", "lado"},
        {"ao lado do teatro e da música."},
        {"egito", "como"},
        {"No antigo Egito já se realizava as chamadas danças astroteológicas em homenagem a um Deus"},
        {"qual", "deus"},
        {"a Osíris"},
        {"Grécia", "como"},
        {" Na Grécia, a dança era frequentemente vinculada aos jogos"},
        {"quais", "jogos"},
        {"Em especial aos olímpicos"},
        {"se", "caracteriza"},
        {"A dança se caracteriza pelo uso do corpo seguindo movimentos previamente estabelecidos"},
        {"que", "seria"},
        {"(coreografia)"},
        {"maioria", "vezes"},
        {"Na maior parte dos casos, a dança, com passos cadenciados é acompanhada ao som e compasso de música"},
        {"envolve", "o que"},
        {"envolve a expressão de sentimentos potenciados por ela"},
        {"exitir", "como"},
        {"A dança pode existir como manifestação artística ou como forma de divertimento ou cerimônia."},
        {"como", "arte"},
        {"Como arte, a dança se expressa através dos signos de movimento, com ou sem ligação musical, para um determinado público, que ao longo do tempo foi se desvinculando das particularidades do teatro."},
        {"atualmente", "como"},
        {"Atualmente, a dança se manifesta nas ruas em eventos como 'Dança em Trânsito', sob a forma de vídeo, no chamado 'vídeodança', e em qualquer outro ambiente em que for contextualizado o propósito artístico."},
        {"dança", "cênica"},
        {"A história da dança cênica representa uma mudança de significação dos propósitos artísticos através do tempo"},
        {"conta", "história", "cênica"},
        {"Com o Balé Clássico, as narrativas e ambientes ilusórios é que guiavam a cena. Com as transformações sociais da época moderna, começou-se a questionar certos virtuosismos presentes no balé e começaram a aparecer diferentes movimentos de Dança Moderna. É importante notar que nesse momento, o contexto social inferia muito nas realizações artísticas, fazendo com que então a Dança Moderna Americana acabasse por se tornar bem diferente da Dança Moderna Europeia, mesmo que tendo alguns elementos em comum."},
        {"dança", "comtemporanea"},
        {"A dança contemporânea como nova manifestação artística"},
        {"influência", "passados"},
        {"sofrendo influências tanto de todos os movimentos passados"},
        {"novas", "possibilidades"},
        {"como das novas possibilidades tecnológicas (vídeo, instalações)."},
        {"novas", "influências"},
        {"Foi essa também muito influenciada pelas novas condições sociais - individualismo crescente, urbanização, propagação e importâncias da mídia, fazendo surgir novas propostas de arte, provocando também fusões com outras áreas artísticas como o teatro por exemplo."},
        {"dança", "educação"},
        {"A dança no contexto educacional brasileiro aparece como conteúdo da disciplina Artes3 e nas atividades rítmicas e expressivas da Educação Física"},
        {"matéria", "disciplina", "arte"},
        {"Na disciplina Arte a dança é trabalhada como atividade e linguagem artística, forma de expressão, socialização, como conceito e linguagem estética de arte corporal. Como atividade de arte cênica e para apresentações."},
        {"matéria", "educação", "fisica"},
        {"Ja na educação física o propósito da dança é diferente podendo até se inserir como cultura corporal de movimento humano."},
        {"abordagem", "diferente"},
        {"Mas a abordagem da dança dentro do contexto da Educação Física é diferente da abordagem da dança no contexto da Arte."},
        {"dança", "educação", "fisica"},
        {"Na educação física a dança é utilizada de forma instrumental, assim como a ginástica, os esportes e as lutas,"},
        {"foca", "aonde", "fisica"},
        {"Deve enfocar o aspecto motor, biopsicossocial, como forma de atividade para condicionamento físico, emagrecimento, bem estar e saúde."},
        {"formação", "academico"},
        {"No âmbito de formação acadêmico-profissional, existem graduações e pós graduações específicas na área de dança."},
        {"bacharelados", "dança"},
        {"Os bacharelados em Dança que qualificam profissionais de dança, seja o artista bailarino, dançarino ou coreógrafo e ainda as licenciaturas em Dança que forma os professores de dança. Estes cursos são vinculados à área de conhecimento das Artes."},
        {"brasil", "formação"},
        {"No Brasil, a formação para professores e artistas de dança é adquirida nos cursos superiores de dança (bacharelados e licenciaturas)."},
        {"qual", "lei"},
        {"Sendo esta profissão regulamentada pela Lei 6.533/78 a Lei do Artista.5"},
        {"ela", "é", "regulamentada"},
        {"sim, quer saber a lei?"},
        {"o que", "é", "eua"},
        {"Os Estados Unidos são uma nação multicultural, lar de uma grande variedade de grupos étnicos, tradições e valores."},
        {"cultura", "comun"},
        {" A cultura em comum pela maioria dos americanos é a cultura ocidental em grande parte derivada das tradições de imigrantes europeus, com influências de muitas outras fontes"},
        {"tradições", "trazidas"},
        {"tradições trazidas pelos escravos da África"},
        {"asia", "latina"},
        {"A imigração mais recente da Ásia e especialmente da América Latina adicionou uma mistura cultural que tem sido descrita tanto como homogeneizada quanto heterogênea, já que os imigrantes e seus descendentes mantêm especificidades culturais"},
        {"individualismo", "como", "eua"},
        {"De acordo com a análise de dimensões culturais de Geert Hofstede, os Estados Unidos têm maior pontuação de individualismo do que qualquer país estudado."},
        {"quais", "jogos"},
        {"Em especial aos olímpicos"},
        {"cultura", "dominante", "eua"},
        {"Apesar da cultura dominante de que os Estados Unidos sejam uma sociedade sem classes"},
        {"estudiosos", "identificam"},
        {"estudiosos identificam diferenças"},
        {"que", "tipo"},
        {"significativa"},
        {"entre", "o que"},
        {" entre as classes sociais do país"},
        {"afetam", "como"},
        {"que afetam a socialização, linguagem e valores"},
        {"classe", "média"},
        {"A classe média e profissional estadunidense iniciou muitas tendências sociais contemporâneas"},
        {"como", "o que"},
        {"como o feminismo moderno, o ambientalismo e o multiculturalismo."},
        {"autoimagem", "estadunidense"},
        {"A autoimagem dos estadunidenses, dos pontos de vista social e de expectativas culturais, é relacionada com as suas profissões em um grau "},
        {"qual", "grau"},
        {"de proximidade incomum"},
        {"valorizam", "o que"},
        {" Embora os americanos tendam a valorizar muito a realização sócio-econômica"},
        {"parte", "média"},
        {"ser parte da classe média ou normal é geralmente visto como um atributo positivo"},
        {"analistas", "acretitam"},
        {"alguns analistas acreditam que os Estados Unidos têm menos mobilidade social que a Europa Ocidental e o Canadá"},
        {"mulheres", "eua"},
        {"As mulheres na sua maioria trabalham fora de casa e recebem a maioria dos diplomas de bacharel."},
        {"casamento", "estastisticas"},
        {"Em 2007, 58% dos estadunidenses com 18 anos ou mais eram casados, 6% eram viúvos, 10% eram divorciados e 25% nunca haviam sido casados."},
        {"casamento", "mesmo", "sexo"},
        {"O casamento entre pessoas do mesmo sexo é um tema controverso no país."},
        {"estados", "permitem"},
        {"Alguns estados permitem uniões civis, em vez do casamento. "},
        {"atualmente", "pode"},
        {"Desde 2003, vários estados têm permitido o casamento entre homossexuais, como resultado de ação judicial ou legislativa, enquanto os eleitores em mais de uma dezena de Estados proibiram a prática através de referendos."},
        {"esportes", "eua"},
        {"Desde finais do século XIX, o beisebol é considerado como o esporte nacional"},
        {"outros", "esportes", "eua"},
        {"enquanto o futebol americano, o hóquei no gelo e o basquete são outros três grandes esportes de equipe profissionais."},
        {"ligas", "universitarias"},
        {"As ligas universitárias também atraem grandes audiências."},
        {"esporte", "mais", "popular"},
        {"O futebol americano é o esporte mais popular no país."},
        {"esporte", "individuais"},
        {"O boxe e a corrida de cavalo foram uma vez os esportes individuais mais vistos"},
        {"foram", "vistos"},
        {"mas foram substituídos pelo golfe e o automobilismo"},
        {"futebol", "normal"},
        {" O futebol vem crescendo de popularidade desde a criação da MLS."},
        {"gosto", "música"},
        {"que legal! de que tipo de música você gosta?"},
        {"pop"},
        {"ah, legal... você sabia que a palavra pop é uma abreviação da palavra popular?"},
        {"música", "pop", "não"},
        {"ah... você gosta de mágica?"},
        {"você", "gosta", "música"},
        {"gosto, e você?"},
        {"rock"},
        {"ah, legal... A maioria das bandas de rock é formada por um vocalista, um guitarrista, um baixista e um baterista :)"},
        {"adoro", "guitarra"},
        {"a guitarra nasceu do violão"},
        {"indie"},
        {"a palavra indie vem da palavra independente "},
        {"forró"},
        {"São tocados, tradicionalmente, por trios, compostos de um sanfoneiro, um zabumbeiro e um tocador de triângulo."},
        {"adoro", "sanfona"},
        {"a sanfona é um instrumento musical aerofone de origem alemã"},
        {"música", "eletrônica"},
        {"música eletrônica é toda música criada através do uso de equipamentos e instrumentos eletrônicos"},
        {"adoro", "cantar"},
        {"legal! você gosta de algum instrumento musical?"},
        {"adoro", "baixo"},
        {"o baixo faz a ligação entre a guitarra/violão/teclado/saxofone com a bateria/percussão"},
        {"adoro", "bateria"},
        {"As baterias são tocadas com o auxílio de um par de baquetas, vassourinhas ou bilros, embora no caso de alguns executantes, possam também ser usadas as próprias mãos."},
        {"adoro", "zabumba"},
        {"zabumba é um tambor confeccionado de pranchas de madeira coladas com veios alternados ou metal, no formato de caixas cilíndricas"},
        {"adoro", "tocar", "triângulo"},
        {"Normalmente é feito de ferro ou aço, mas podem ser encontrados em alumínio."},
        {"adoro", "funk"},
        {"O funk é um gênero musical que se originou nos EUA na segunda metade da década de 1960, quando músicos afro-americanos, misturando soul, jazz e rhythm and blues, criaram uma nova forma de música rítmica e dançante."},
        {"soul", "?"},
        {"Soul é um gênero musical dos Estados Unidos que nasceu do rhythm and blues e do gospel durante o final da década de 1950 e início da década de 1960 entre os negros."},
        {"adoro", "jazz"},
        {"O jazz se desenvolveu com a mistura de várias tradições musicais, em particular a afro-americana."},
        {"rhythm and blues", "?"},
        {"Em 15 de fevereiro de 1949, surgiu pela primeira vez a expressão Rhythm & Blues (mais conhecida como R&B), criada por Jerry Wexler, redator da revista Billboard; inicialmente o termo era usado para designar toda e qualquer música popular afro-americana, mas com o surgimento do Rock'n'Roll, acabaria se tornando sinônimo de Blues balançado, dançante."},
        {"adoro", "gospel"},
        {"é um gênero musical composto e produzido para expressar a crença, individual ou comunitária, cristã."},
        {"adoro", "punk"},
        {"A música punk é um dos elementos que mais caracteriza a cultura punk. Acompanhando as mudanças históricas do estilo, a música punk também apresenta um grande número de ramificações e evoluções."},
        {"odeio", "música"},
        {"mas por quê? música é um tipo de arte muito importante e que nos faz refletir sobre tudo o que acontece sobre nós"},
        {"odeio", "funk"},
        {"mas qual funk? o carioca ou o norte-americano?"},
        {"funk", "carioca"},
        {"devemos respeitar as pessoas independentemente de suas preferências e seus costumes"},
        {"funk", "norte-americano"},
        {"eu gosto de alguns funkeiros norte-americanos, devemos respeitar as pessoas independentemente de suas preferências e seus costumes"},
        {"odeio", "forró"},
        {"mas por quê? forró é um tipo de música típica da região nordeste do Brasil. Nunca se imaginou dançando forró?"},
        {"dançando", "forró", "não"},
        {"pois deveria experimentar quando tiver oportunidade."},
        {"dançando", "forró", "sim"},
        {"e não despertou a vontade de dançar de verdade?"},
        {"não", "despertou"},
        {"poxa, que pena... dança é um ótimo jeito de expressar a arte através do corpo"},
        {"sim", "despertou"},
        {"viu, não podemos falar mal de algo que nem conhecemos direito"},
        {"música", "mais", "longa"},
        {"a música mais longa do mundo se chama As Slow As Possible (O mais devagar possível).Composta por John Cage em 1987, ela não deveria passar de 20 a 70 minutos. SÓ QUE o compositor nunca especificou o tempo das notas. Daí um grupo de filósofos e músicos decidiu reduzir o ritmo de tal forma que a música levasse 639 anos para finalizar. Sim, apenas 639 anos. E já começou! Eles começaram a tocar a As Slow As Possible em 05 de setembro de 2001, numa igreja em Halberstadt, Alemanha, com término previsto para 2640."},
        {"gosta", "mágica"},
        {"adoro e você?"},
        {"adoro", "mágica"},
        {"você sabe fazer algum truque de mágica?"},
        {"sei", "cartas"},
        {"mágicas com o uso de cartas de baralho são muito comuns nos shows de mágica"},
        {"sei", "coelho", "cartola"},
        {"o truque de tirar o coelho da cartola é o truque mais clássico nos shows de mágica"},
        {"adoro", "palhaço"},
        {"O palhaço não interpreta, ele simplesmente é. Ele não é uma personagem, ele é o próprio ator expondo seu ridículo, mostrando sua ingenuidade."},
        {"odeio", "palhaço"},
        {"muita gente tem medo de palhaço. Às vezes o medo é adquirido após experiências traumáticas com um indivíduo singular, ou após ver algum palhaço ameaçador na mídia."},
        {"sou", "palhaço"},
        {"você gosta de contar piadas então?"},
        {"quero", "ser", "mágico"},
        {"para ser mágico exige muito treinamento e principalmente muita habilidade com as suas técnicas de mágica"},
        {"quero", "ser", "palhaço"},
        {"você é bom em contar piadas e fazer gracinhas? se não for, é bom ir treinando..."},
        {"adoro", "circo"},
        {"o circo reúne artistas de diferentes especialidades, como malabarismo, palhaço, acrobacia, monociclo, contorcionismo, equilibrismo, ilusionismo, entre outros."},
        {"adoro", "malabarismo"},
        {"Malabarismo é a arte de manipular objetos com destreza. É uma das mais típicas artes de circo."},
        {"adoro", "acrobacia"},
        {"Acrobacias são performances de destreza corporal comuns em circos. Elementos como o trapézio, pêndulos e outros tipos de balanços com alta altitude são utilizados para transformar a Acrobacia em um número de circo mais emocionante."},
        {"adoro", "monociclo"},
        {"Monociclo é um meio de transporte utilizado principalmente para apresentações artísticas que envolvem o equilibrismo."},
        {"adoro", "contorcionismo"},
        {"A contorção é uma arte circense na qual seu objetivo é destacar a possibilidade física de contorcionismo do corpo humano."},
        {"adoro", "equilibrismo"},
        {"Equilibrismo é um termo genérico que abrange uma ampla gama de habilidades físicas que envolvem a capacidade de manter o equilíbrio."},
        {"adoro", "ilusionismo"},
        {"Ilusionismo (ou mais conhecido popularmente por mágica)é a arte performativa que tem como objetivo entreter o público dando a ilusão de que algo impossível ou sobrenatural ocorreu."},
        {"gosta", "circo"},
        {"adoro o circo, e você?"},
        {"quero", "ser", "equilibrista"},
        {"para isso, você deverá entrar em uma escola de circo e treinar muito"},
        {"que", "é", "monociclo"},
        {"o monociclo caracteriza-se por ter apenas uma roda. Utilizado principalmente para apresentações artísticas que envolvem o equilibrismo."},
        {"quero", "ser", "contorcionista"},
        {"para isso, você terá que treinar muito, ter muito cuidado, atenção e foco no que você está fazendo."},
        {"mulher", "barbada"},
        {"Estas mulheres há muito têm sido consideradas como atrações circenses, curiosidades teratológicas ou aberrações humanas."},
        {"animais", "circo"},
        {"Atualmente é proibido o uso de animais no circo em algumas cidades, mas na maioria dos municípios brasileiros ainda é permitida sua exibição, tendo em vista que não há uma legislação federal que regule a matéria."},
        {"circo", "história"},
        {"a origem do circo é dos chineses aos gregos, dos egípcios aos indianos, quase todas as civilizações antigas já praticavam algum tipo de arte circense há pelo menos 4 000 anos - mas o circo como o conhecemos hoje só começou a tomar forma durante o Império Romano."},
        {"engolidor", "fogo"},
        {"Os engolidores de fogo apareceram pela primeira vez no Império Romano, nos espetáculos do Circus Maximus, há mais de 2 000 anos. Esses prófugos costumavam ser nórdicos louros e altos, o que acentuava o exotismo"},
        {"palhaço", "cavalo"},
        {"Na origem do circo moderno, na Inglaterra do século XVIII, o palhaço e o equilibrismo com piruetas sobre um cavalo eram um só número. O sucesso foi tão grande que os clowns ganharam cada vez mais espaço no picadeiro."},
        {"criança", "trabalhar", "circo"},
        {"Existe uma lei feita para as crianças que trabalham no circo. Ela obriga as escolas municipais e estaduais de todas as cidades a receberem alunos nômades, mesmo quando não possuem vagas."},
        {"tempo", "montar", "circo"},
        {"A montagem de um circo consome em média 8 horas. Desmontá-lo, por sua vez, leva a metade do tempo."},
        {"circo", "mais", "antigo"},
        {"O Astley’s Amphitheatre (Anfiteatro de Ashley), em Londres, é o circo moderno mais antigo do mundo."},
        {"circo", "chegou", "Brasil"},
        {"O circo chegou ao Brasil no final do século 19."},
        {"escola", "circo", "Brasil"},
        {"A primeira escola de circo brasileira foi a Piolim, fundada em 1977 em São Paulo."},
        {"adoro", "animais"},
        {"qual o seu animal preferido?"},
        {"animal", "preferido", "cachorro"},
        {"você tem um?"},
        {"tenho", "cachorro"},
        {"nossa que legal! cachorros são ótimos animais domésticos, e dependendo da raça, são ótimos seguranças para casa também"},
        {"gosta", "animais"},
        {"gosto, você tem algum animal? qual?"},
        {"tenho", "gato"},
        {"gatos são animais meio preguiçosos e astutos, são mais independentes que os cachorros"},
        {"animal", "preferido", "gato"},
        {"gatos são felinos, ou seja, são da mesma família que os leões, onças, tigres e leopardos"},
        {"tenho", "papagaio"},
        {"papagaios são animais algumas vezes utilizados em apresentações ao público, geralmente em apresentações que têm como objetivo fazer o público rir"},
        {"tenho", "peixinho"},
        {"poxa, que legal, eu tinha um peixinho dourado quando era criança"},
        {"adoro", "flores"},
        {"flores têm cheiro ótimo, são muito usadas para enfeitar os lugares e também muito utilizados por um homem apaixonada que deseja dar á sua mulher amada."},
        {"gosta", "flores"},
        {"eu adoro flores, além de terem um ótimo cheiro, também são muito bonitas"},
        {"adoro", "floresta"},
        {"poxa, mas que ótimo! o ar de lá é muito mais limpo do que o ar da cidade e é ótimo conhecer lugares novos"},
        {"gosta", "floresta"},
        {"amo visitar qualquer floresta. O ar das florestas é ótimo, sem mencionar que é ótimo pra acampar"},
        {"animal", "preferido", "dinossauro"},
        {"os dinossauros apareceram há pelo menos duzentos e trinta milhões de anos"},
        {"animal", "preferido", "leão"},
        {"Leões assim como os gatos, são felinos só que de porte físico maior"},
        {"animal", "preferido", "papagaio"},
        {"Papagaios são aves e também o símbolo do time Palmeiras"},
        {"gosto", "acampar"},
        {"Acampar é uma ótima forma para relaxar"},
        {"fauna", "flora"},
        {"a união da fauna e da flora é conhecida como ecossistema. No nosso país temos 6 principais ecossistemas: pantanal, campos, mata atlântica, floresta amazônica, cerrado e caatinga."},
        {"gosto", "boto"},
        {"ei... tem uma lenda chamada Boto cor-de-rosa, conhece? eu adoro"},
        {"gosto", "girafa"},
        {"Atualmente estão listadas nove subespécies de girafa, diferenciadas pela distribuição geográfica e pelo padrão das manchas."},
        {"gosto", "tigre"},
        {"O tigre (Panthera tigris) é um mamífero da família dos felídeos. É um dos quatro grandes gatos do género Panthera. Os tigres são predadores carnívoros."},
        {"gosto", "margaridas"},
        {"As folhas da margarida têm forma espatulada, com algumas variações, com margem crenada ou serrada, com alguma penugem, pelo menos na sua forma juvenil."},
        {"gosto", "rosas"},
        {"A primeira rosa cresceu nos jardins asiáticos há 5 000 anos. Na sua forma selvagem, a flor é ainda mais antiga. Fósseis dessas rosas datam de há 35 milhões de anos."},
        {"gosto", "morcego"},
        {"O morcego é um animal mamífero da ordem Chiroptera cujos membros superiores (braços e mãos) têm formato de asas membranosas, tornando-os os únicos mamíferos naturalmente capazes de voar."},
        {"subespécies", "girafa"},
        {"as subespécies da girafa são nove: Girafa-reticulada ou girafa-da-somália, Girafa-angolana, G.c. antiquorum, Girafa-do-kilimanjaro ou girafa-masai, Girafa-núbia, Girafa-de-rothschild, Girafa-sul-africana, Girafa-da-rodésia, Girafa-do-Chade."},
        {"extinção", "animais"},
        {"Ações humanas, como o desmatamento, queimadas, excesso de lixo e poluição, têm prejudicado o meio ambiente. Assim, a flora (plantas) e fauna (animais) acabam sendo muito prejudicadas."},
        {"maior", "jardim", "praia"},
        {"A cidade de Santos (SP) tem o maior jardim de praia do mundo (218.800 m²) que molduram as praias com 1.746 espécies vegetais, reconhecidos pelo Guinness Records."},
        {"maior", "biodiversidade", "planeta"},
        {"o Brasil possui a maior biodiversidade vegetal do planeta, com mais de 55 mil espécies de plantas superiores e cerca de 10 mil de briófitas, fungos e algas, um total equivalente a quase 25% de todas as espécies de plantas existentes."},
        {"nome", "comum", "cachorro"},
        {"Os três nomes mais comuns de cães são: Lady, King e Duke."},
        {"morcego", "quando", "chove"},
        {"Na América do Sul há uma espécie de morcegos que constrói tendas com as folhas das árvores."},
        {"tempo", "voo", "galinha"},
        {"O recorde de tempo de voo de uma galinha é de 13 segundos."},
        {"quero", "ser", "médico"},
        {"Os médicos podem ser generalistas, isto é, não especializados em nenhuma área específica da medicina, ou especialistas, quando especializados em alguma área. O curso de medicina dura 5 anos e requer muito estudo e dedicação."},
        {"quero", "ser", "médica"},
        {"Os médicos podem ser generalistas, isto é, não especializados em nenhuma área específica da medicina, ou especialistas, quando especializados em alguma área. O curso de medicina dura 5 anos e requer muito estudo e dedicação."},
        {"quero", "ser", "dentista"},
        {"Visitas ao dentista consistem, na maioria das vezes, em revisão da higiene bucal e integridade dos dentes, incluindo limpeza e profilaxia. Devem ser repetidas a cada seis meses, com o objetivo de manter os dentes em um estado adequado e evitar que se desenvolvam problemas bucais."},
        {"quero", "ser", "professor"},
        {"É uma das profissões mais antigas e mais importantes, tendo em vista que as demais, na sua maioria, dependem dela."},
        {"quero", "ser", "professora"},
        {"É uma das profissões mais antigas e mais importantes, tendo em vista que as demais, na sua maioria, dependem dela."},
        {"quero", "ser", "carteiro"},
        {"As características desejáveis para ser um carteiro são: ter bom condicionamento físico para percorrer longas distâncias cotidianamente (carteiros caminham aproximadamente 20 km/dia) e suportar diversas condições climáticas como intenso calor, frio ou chuva; senso de responsabilidade; carisma; metodologia; agilidade, bom relacionamento interpessoal; dinamismo e raciocínio rápido e automotivação."},
        {"quero", "ser", "carteira"},
        {"As características desejáveis para ser um carteiro são: ter bom condicionamento físico para percorrer longas distâncias cotidianamente (carteiros caminham aproximadamente 20 km/dia) e suportar diversas condições climáticas como intenso calor, frio ou chuva; senso de responsabilidade; carisma; metodologia; agilidade, bom relacionamento interpessoal; dinamismo e raciocínio rápido e automotivação."},
        {"quero", "ser", "cozinheiro"},
        {"hmmmm deu até água na boca agora... volte para conversar comigo de novo quando o seu restaurante estiver pronto, mal posso esperar para experimentar as delícias do seu futuro restaurante"},
        {"quero", "ser", "cozinheiro"},
        {"hmmmm deu até água na boca agora... volte para conversar comigo de novo quando o seu restaurante estiver pronto, mal posso esperar para experimentar as delícias do seu futuro restaurante"},
        {"quero", "ser", "presidente"},
        {"Em uma república presidencialista, o (a) presindente é a autoridade máxima do Poder Executivo e da República, cabendo a ele/ela as tarefas de chefe de Estado e o chefe de governo."},
        {"quero", "ser", "astronauta"},
        {"poxa! que excelente isso!! A pessoa mais jovem a voar no espaço foi o russo Gherman S. Titov, quando voou na Vostok 2 aos 25 anos de idade (sendo também o primeiro astronauta a sofrer de síndrome de adaptação ao espaço), e a mais velha foi John Glenn, quando voou na missão STS-95 aos 77 anos de idade."},
        {"síndrome", "adaptação", "espaço"},
        {"Síndrome de adaptação ao espaço, ou doença do espaço, é o que sentem os astronautas durante a adaptação à micro gravidade. Ela está relacionada à doença do movimento, quando o aparato vestibular se adapta ao estado de queda livre. O mal do espaço era realmente desconhecido durante os primeiros voos espaciais, visto que estes eram feitos sob condições bastante restritivas; parece ser agravado quando se é capaz de movimentar-se livremente em todas as direções, e portanto é mais comum em grandes espaçonaves."},
        {"quero", "ser", "policial"},
        {"Apesar de ser normalmente associada exclusivamente à atividade de aplicação da lei, a atividade policial é bastante mais abrangente. Para além da preservação da lei e da ordem, a polícia pode incluir outras atividades como o socorro em situações de acidente ou catástrofe, o planeamento urbano, a educação de menores e até a assistência social"},
        {"quero", "ser", "veterinário"},
        {"A medicina veterinária é a ciência médica que se dedica à prevenção, controle, erradicação e tratamento das doenças, traumatismos ou qualquer outro agravo à saúde dos animais, além do controle da sanidade dos produtos e subprodutos de origem animal para o consumo humano."},
        {"quero", "ser", "veterinária"},
        {"A medicina veterinária é a ciência médica que se dedica à prevenção, controle, erradicação e tratamento das doenças, traumatismos ou qualquer outro agravo à saúde dos animais, além do controle da sanidade dos produtos e subprodutos de origem animal para o consumo humano."},
        {"quero", "ser", "artista"},
        {"nossa, que legal! que tipo de artista? existe cantor, pintor, escultor... entre outros"},
        {"cantor"},
        {"legal! que tipo de música você cantaria?"},
        {"cantora"},
        {"legal! que tipo de música você cantaria?"},
        {"pintor"},
        {"nossa! que legal uma das maiores artistas brasileiras é a Tarsila do Amaral, um dos seus quadros mais famosos é o quadro chamado Abaporu"},
        {"pintora"},
        {"nossa! que legal uma das maiores artistas brasileiras é a Tarsila do Amaral, um dos seus quadros mais famosos é o quadro chamado Abaporu"},
        {"escultor"},
        {"Escultura é uma arte que representa imagens plásticas em relevo total ou parcial."},
        {"escultora"},
        {"Escultura é uma arte que representa imagens plásticas em relevo total ou parcial."},
        {"quero", "ser", "piloto"},
        {"pilotar é uma área interessante... mas existem vários tipos de piloto, por exemplo, piloto de fórmula 1, piloto de avião, piloto de foguete ou ônibus espacial, etc"},
        {"quero", "ser", "pilota"},
        {"pilotar é uma área interessante... mas existem vários tipos de piloto, por exemplo, piloto de fórmula 1, piloto de avião, piloto de foguete ou ônibus espacial, etc"},
        {"quero", "ser", "engenheiro"},
        {"nossa, que excelente! a área da engenharia é uma área bem vasta, espero que você escolha bem o que você gosta"},
        {"quero", "ser", "engenheira"},
        {"nossa, que excelente! a área da engenharia é uma área bem vasta, espero que você escolha bem o que você gosta"},
        {"quero", "ser", "físico"},
        {"nossa, que excepcional! física é uma área muito complexa e requer muita dedicação e foco"},
        {"quero", "ser", "física"},
        {"nossa, que excepcional! física é uma área muito complexa e requer muita dedicação e foco"},
        {"ser", "jogador", "futebol"},
        {"que tipo de futebol? Americano ou o nosso conhecido futebol?"},
        {"ser", "jogadora", "futebol"},
        {"que tipo de futebol? Americano ou o nosso conhecido futebol?"},
        {"futebol"},
        {"É jogado num campo retangular gramado, com uma baliza em cada lado do campo. O objetivo do jogo é deslocar uma bola através do campo para colocá-la dentro da baliza adversária, ação que se denomina gol."},
        {"futebol", "americano"},
        {" É frequente ver no futebol americano uma metáfora para a guerra, com muita violência pessoal a ter lugar dentro do campo, com jogadores pesando 150 kg ou mais a empurrar-se mutuamente com cada grama do seu peso, e com uma linha de frente claramente definida, que se move para trás e para a frente ao longo do campo, separando as equipes de ataque e defesa."},
        {"quero", "ser", "modelo"},
        {"bom, não se esqueça de nunca trocar uma dieta saudável por bulimia ou anorexia"},
        {"quero", "ser", "skatista"},
        {"na década de 1960, os surfistas da Califórnia mais ou menos na cidade de Los Angeles queriam fazer das pranchas um divertimento também nas ruas, em uma época de marés baixas e seca na região, assim nasceu o skate"},
        {"quero", "ser", "surfista"},
        {"Utilizavam-se, inicialmente, no Hawaii pranchas de madeira e, no Peru, de junco. As pranchas eram fabricadas pelos próprios usuários. Acreditava-se que, ao fabricar sua própria prancha, se transmitiam todas as energias positivas para ela e, ao se praticar o esporte, se libertava das energias negativas."},
        {"quero", "ser", "bombeiro"},
        {"O serviço de combate a incêndios e serviços de resgate é conhecido em alguns países como brigada de incêndio. Os bombeiros tornaram-se onipresentes em todo mundo, das áreas florestais para as áreas urbanas e a bordo de navios."},
        {"quero", "ser", "bombeira"},
        {"O serviço de combate a incêndios e serviços de resgate é conhecido em alguns países como brigada de incêndio. Os bombeiros tornaram-se onipresentes em todo mundo, das áreas florestais para as áreas urbanas e a bordo de navios."},
        {"quero", "ser", "economista"},
        {"O alto status dos economistas ou Cientistas Econômicos perante a sociedade é resultado da quantidade de informação que o estudante recebe durante o curso superior. A capacidade de entender fluxos financeiros, prever tendências mercadológicas e gerir investimentos de maneira precisa são algumas das qualidades que os permitem trabalhar em qualquer área financeira."},
        {"quero", "ser", "matemático"},
        {"Um matemático é uma pessoa com um grande conhecimento de matemática e que utiliza esse conhecimento em seu trabalho, geralmente para resolver problemas matemáticos. A matemática lida com números, dados, coleções, quantidades, estruturas, espaço, modelos e variações."},
        {"quero", "ser", "matemática"},
        {"Um matemático é uma pessoa com um grande conhecimento de matemática e que utiliza esse conhecimento em seu trabalho, geralmente para resolver problemas matemáticos. A matemática lida com números, dados, coleções, quantidades, estruturas, espaço, modelos e variações."},
        {"quero", "ser", "jornalista"},
        {"Jornalismo é uma atividade de Comunicação.1 Em uma sociedade moderna, os meios de comunicação tornaram-se os principais fornecedores de informação e opinião sobre assuntos públicos, mas o papel do jornalismo, juntamente com outras formas de mídia, está sofrendo modificações, decorrentes da expansão da Internet."},
        {"quero", "ser", "cabeleireiro"},
        {"Estes profissionais utilizam vários utensílios e ferramentas para a manipulação capilar, onde se salientam: as tesouras, navalhas, pentes, capas e máquinas de corte e acabamento."},
        {"quero", "ser", "cabeleireira"},
        {"Estes profissionais utilizam vários utensílios e ferramentas para a manipulação capilar, onde se salientam: as tesouras, navalhas, pentes, capas e máquinas de corte e acabamento."},
        {"quero", "ser", "advogado"},
        {"Um advogado é um profissional liberal, bacharel em Direito e autorizado pelas instituições competentes de cada país a exercer o jus postulandi, ou seja, a representação dos legítimos interesses das pessoas físicas ou jurídicas em juízo ou fora dele, quer entre si, quer ante o Estado."},
        {"quero", "ser", "advogada"},
        {"Um advogado é um profissional liberal, bacharel em Direito e autorizado pelas instituições competentes de cada país a exercer o jus postulandi, ou seja, a representação dos legítimos interesses das pessoas físicas ou jurídicas em juízo ou fora dele, quer entre si, quer ante o Estado."},
        {"quero", "ser", "arquiteto"},
        {"Um arquiteto é o profissional responsável pelo projeto, supervisão e execução de obras de arquitetura. Embora esta seja sua principal atividade, o campo de atuação de um arquiteto envolve todas as áreas correlatas ao controle e desenho do espaço habitado, como o urbanismo, o paisagismo, e diversas formas de design."},
        {"quero", "ser", "arquiteta"},
        {"Um arquiteto é o profissional responsável pelo projeto, supervisão e execução de obras de arquitetura. Embora esta seja sua principal atividade, o campo de atuação de um arquiteto envolve todas as áreas correlatas ao controle e desenho do espaço habitado, como o urbanismo, o paisagismo, e diversas formas de design."},
        {"quero", "ser", "biólogo"},
        {"Desenvolve seus estudos por meio do método científico. Trabalha em laboratórios de pesquisa, laboratórios de rotina como os de biologia clínica, campos abertos como savanas, florestas e todo lugar onde há vida para ser estudada."},
        {"quero", "ser", "bióloga"},
        {"Desenvolve seus estudos por meio do método científico. Trabalha em laboratórios de pesquisa, laboratórios de rotina como os de biologia clínica, campos abertos como savanas, florestas e todo lugar onde há vida para ser estudada."},
        {"gosto", "arte"},
        {"poxa! que legal... que tipo de arte você gosta? existem vários tipos de arte, como por exemplo,  arquitetura, escultura, pintura, escrita, música, dança e cinema, em suas variadas combinações."},
        {"gosto", "arquitetura"},
        {"A arquitetura refere-se à arte ou a técnica de projetar e edificar o ambiente habitado pelo ser humano.  Neste sentido, a arquitetura trata destacadamente da organização do espaço e de seus elementos: em última instância, a arquitetura lidaria com qualquer problema de agenciamento, organização, estética e ordenamento de componentes em qualquer situação de arranjo espacial. No entanto, normalmente a arquitetura associa-se diretamente ao problema da organização do homem no espaço (e principalmente no espaço urbano)."},
        {"gosto", "escultura"},
        {"Escultura é uma arte que representa imagens plásticas em relevo total ou parcial. Existem várias técnicas de trabalhar os materiais, como a cinzelação, a fundição, a moldagem ou a aglomeração de partículas para a criação de um objeto."},
        {"cinzelação"},
        {"A cinzelação é a aplicação de peças metálicas (ouro, bronze, prata, etc) em obras de artes esculpidas."},
        {"fundição"},
        {"Na metalurgia, a fundição é o processo de vazar metal líquido em um molde, que contém uma cavidade com a forma desejada, e depois permitir que resfrie e solidifique."},
        {"moldagem"},
        {"Moldagem é o processo mecânico onde são obtidas peças utilizando matéria-prima não sólida. Esta pode estar em formato líquido, de pó ou de argila. Quando a matéria-prima é um pó, a solidificação pode ser feita através da adição de um líquido aglomerante ou por aquecimento. Nos outros casos, o enrijecimento pode acontecer pelo simples contato com o ar."},
        {"gosto", "pintura"},
        {"A pintura refere-se genericamente à técnica de aplicar pigmento em forma líquida a uma superfície, a fim de colori-la, atribuindo-lhe matizes, tons e texturas. Em um sentido mais específico, é a arte de pintar uma superfície, tais como papel, tela, ou uma parede (pintura mural ou de afrescos). A pintura a óleo é considerada por muitos como um dos suportes artísticos tradicionais mais importantes."},
        {"gosto", "escrita"},
        {"Escrita ou grafia consiste na utilização de sinais (símbolos) para exprimir as ideias humanas . A grafia é uma tecnologia de comunicação, historicamente criada e desenvolvida na sociedade humana, e basicamente consiste em registrar marcas em um suporte."},
        {"gosto", "música"},
        {"A música é uma forma de arte que se constitui basicamente em combinar sons e ritmo seguindo uma pré-organização ao longo do tempo."},
        {"gosto", "dança"},
        {"A dança é uma das três principais artes cênicas da Antiguidade, ao lado do teatro e da música. No antigo Egito já se realizava as chamadas danças astroteológicas em homenagem a Osíris. Na Grécia, a dança era frequentemente vinculada aos jogos, em especial aos olímpicos. A dança se caracteriza pelo uso do corpo seguindo movimentos previamente estabelecidos (coreografia) ou improvisados (dança livre)."},
        {"cinema"},
        {"Cinema é a técnica e a arte de fixar e de reproduzir imagens que suscitam impressão de movimento, assim como a indústria que produz estas imagens. As obras cinematográficas (mais conhecidas como filmes) são produzidas através da gravação de imagens do mundo com câmeras adequadas, ou pela sua criação utilizando técnicas de animação ou efeitos visuais específicos."},
        {"tela", "pintura"},
        {"Uma tela é uma superfície esticada, feita com tecido, utilizada para cobrir um vão sem impedir a passagem de luz."},
        {"pintura", "mural"},
        {"Muralismo, pintura mural ou parietal é a pintura executada sobre uma parede, diretamente na sua superfície, como num afresco, num painel montado numa exposição permanente. Ela difere de todas as outras formas de arte pictórica por estar profundamente vinculada à arquitetura, podendo explorar o caráter plano de uma parede ou criar o efeito de uma nova área de espaço."},
        {"afresco"},
        {"Afresco ou Fresco é o nome dado a uma obra pictórica feita sobre parede, com base de gesso ou argamassa. Assume frequentemente a forma de mural."},
        {"pintura", "óleo"},
        {"A tinta a óleo é uma tinta de secagem lenta que consiste numa mistura de partículas de pigmento em suspensão num óleo secante, sendo o mais comum, o óleo de linhaça. A viscosidade da tinta pode ser alterada pela adição de solvente tal como a Terebentina ou Éter de petróleo. Pode ser adicionado Verniz para aumentar o brilho do filme de tinta a óleo seco. As tintas a óleo têm sido usadas na Europa desde o Século XII para decoração simples e adoptadas largamente a partir do inicio do Século XIV, como meio de expressão artística."},
        {"terebintina"},
        {"Terebintina é um líquido obtido por destilação de resina de coníferas. É um líquido normalmente incolor, mas pode se apresentar levemente colorido por causa de alguma impurezas, com aroma forte e penetrante de pinho (quando fabricado a partir de resina de pinheiro). É um bom solvente, sendo usado na mistura de tintas, vernizes e polidores."},
        {"arte", "grega"},
        {"Por arte da Grécia Antiga compreende-se as manifestações das artes visuais, artes cênicas, literatura, música e arquitetura daquele país desde o início do período geométrico, quando, emergindo da Idade das Trevas, iniciou-se a formação de uma cultura original, até o fim do período helenístico, quando a tradição grega se dissemina por uma larga área entre a Europa, África e Ásia, abrangendo o intervalo de aproximadamente 900 até 146 a.C., data em que a Grécia caiu sob o domínio romano."},
        {"período", "geométrico"},
        {"Designa-se por período geométrico um dos cinco períodos da história da Grécia Antiga, aproximadamente entre 900 a.C. e 750 a.C., e que se sucede ao período protogeométrico. Constitui-se como uma fase da arte grega e é caracterizado pela existência de motivos geométricos na cerâmica, que floresceram no final da Idade das Trevas. O seu centro era Atenas. A arte geométrica foi difundida entre as cidades comerciais das Ilhas Egeias."},
        {"período", "helenístico"},
        {"Caracterizou-se pela difusão da civilização grega numa vasta área que se estendia do mar Mediterrâneo oriental à Ásia Central. De modo geral, o helenismo foi a concretização de um ideal de Alexandre: o de levar e difundir a cultura grega aos territórios que conquistava. Foi naquele período que as ciências particulares tiveram seu primeiro e grande desenvolvimento. Foi o tempo de Euclides e Arquimedes. O helenismo marcou um período de transição para o domínio e apogeu de Roma."},
        {"arquitetura", "grega"},
        {"dentro da arquitetura grega existem 3 ordens gregas: Ordem dórica, Ordem jônica, Ordem coríntia"},
        {"ordem", "dórica"},
        {"É principalmente empregada no exterior de templos dedicados a divindades masculinas e é a mais simples das três ordens gregas definindo um edifício em geral baixo e de carácter sólido."},
        {"ordem", "jônica"},
        {"Desenvolvendo-se paralelamente ao dórico apresenta, no entanto, formas mais fluidas e uma leveza geral, sendo mais utilizado em templos dedicados a divindades femininas."},
        {"ordem", "coríntia"},
        {"utilizado inicialmente só no interior, é um estilo notoriamente mais decorativo e trabalhado."},
        {"tarsila", "amaral"},
        {"Tarsila do Amaral (Capivari, 1 de setembro de 1886 — São Paulo, 17 de janeiro de 1973) foi uma pintora e desenhista brasileira e uma das figuras centrais da pintura brasileira e da primeira fase do movimento modernista brasileiro, ao lado de Anita Malfatti. Seu quadro Abaporu, de 1928, inaugura o movimento antropofágico nas artes plásticas."},
        {"movimento", "modernista"},
        {"Chama-se genericamente modernismo (ou movimento modernista) o conjunto de movimentos culturais, escolas e estilos que permearam as artes e o design da primeira metade do século XX."},
        {"anita", "malfatti"},
        {"Anita Catarina Malfatti (São Paulo, 2 de dezembro de 1889 — São Paulo, 6 de novembro de 1964) foi uma pintora, desenhista, gravadora e professora brasileira."},
        {"abaporu"},
        {"Hoje, é a tela brasileira mais valorizada no mundo, tendo alcançado o valor de US$ 1,5 milhão (aproximadamente 3 milhões de reais), pago pelo colecionador argentino Eduardo Costantini em 1995. Encontra-se exposta no Museu de arte latino-americana de Buenos Aires (MALBA). Abaporu vem dos termos em tupi aba (homem), pora (gente) e ú (comer), significando homem que come gente. O nome é uma referência à antropofagia modernista, que se propunha a deglutir a cultura estrangeira e adaptá-la ao Brasil. Foi pintado em óleo sobre tela em 1928 por Tarsila do Amaral para dar de presente de aniversário ao escritor Oswald de Andrade, seu marido na época."},
        {"movimento", "antropofágico"},
        {"O Movimento Antropofágico foi uma manifestação artística brasileira da década de 1920, fundada e teorizada pelo poeta paulista Oswald de Andrade."},
        {"que", "é", "arte"},
        {"uma forma de representar sentimentos, vivências, ideologias, sonhos, etc. É uma forma de expressão, em outras palavras"},
        {"gosto", "filmes"},
        {"puxa, que legal! qual gênero de filme você gosta? existem vários, mas os mais conhecidos são: ação, comédia, romance, aventura, ficção, suspense, terror, drama, animação, entre outros"},
        {"ação"},
        {"Um filme de ação é um gênero de filme que geralmente envolve uma história de protagonistas do bem contra antagonistas do mal, que resolvem suas disputas com o uso de força física. Os filmes de ação são comuns de se misturarem com os gêneros policiais e crimes, westerns e guerra, entre outros."},
        {"comédia"},
        {"A comédia é o uso de humor nas artes cênicas. Também pode estar presente em um espetáculo, história, ou até mesmo um filme, que recorre intensivamente ao humor. De forma geral, comédia é o que é engraçado, que faz rir."},
        {"romance"},
        {"Os filmes do gênero romance podem ser definidos como aqueles cujo enredo se desenvolve em torno do envolvimento amoroso entre os protagonistas. Um dos pré-requisitos do gênero é de que o filme tenha um final feliz; contudo, alguns filmes com final triste também podem ser considerados filmes do gênero romântico. São os chamados star-crossed lovers, os amantes que não conseguem ficar juntos no final do filme, como é o caso de Romeu e Julieta, Titanic, Brokeback Mountain e a animação Pocahontas."},
        {"aventura"},
        {"O filme de aventura é um gênero cinematográfico que reflete um mundo heroico de combates e aventuras. Foi inventado na Itália, como meio de exaltação de seu passado histórico e, posteriormente, foi usado pela Rússia, para exaltar a Revolução Russa."},
        {"ficção"},
        {"Desde que o cinema começou, se encontra a ficção científica em filmes. Autores de livros que causaram a curiosidade e a imaginação do mundo sobre temas dessa natureza, como H. G. Wells ou Júlio Verne, chamaram também a atenção de artistas de outras mídias. Orson Welles, como radialista, aterrorizou toda a população de Nova York, interpretando no rádio a história do livro Guerra dos Mundos de H. G. Wells, onde grande parte da população acreditou estar mesmo sendo invadida pelos marcianos. Nos dias atuais os filmes do género estão entre os que alcançam maior índice de bilheteira, confirmando a fascinação das pessoas sobre o que está por vir, ou ainda sobre o que é pura fantasia: E.T., Guerra nas estrelas, O exterminador do futuro, De volta para o futuro ou episódios de Jornada nas Estrelas são alguns dos títulos mais conhecidos."},
        {"suspense"},
        {"Thriller ou suspense é um gênero da literatura, filmes, jogos eletrônicos e televisão que usa o suspense, tensão e excitação como principais elementos. O seu principal subgênero é o thriller psicológico. Depois do assassinato do presidente estadunidense John F. Kennedy, os filmes de thriller político e paranoico se tornaram muito populares. Um grande exemplo de thrillers são os filmes de Alfred Hitchcock."},
        {"terror"},
        {"Terror ou Horror é um gênero literário, cinematográfico ou musical, que está sempre muito ligado à ficção e fantasia, e que também pode ser verificado na pintura, no desenho, na gravura e fotografia. A abstrata ideia de terror ou o ato de transmitir o sentimento de terror ou horror pode ser verificado em todas as formas de arte. Ao decorrer da década de 1990, até os dias de hoje, o gênero também compreende um estilo de desenvolvimento de jogos eletrônicos."},
        {"drama"},
        {"O termo encontrado no cinema, na televisão, no rádio, para drama, significa um texto ficcional, peça teatral ou filme de caráter sério, não cômico, que apresenta um desenvolvimento de fatos e circunstâncias compatíveis com os da vida real."},
        {"western"},
        {"O chamado cinema western, também popularizado sob os termos filmes de cowboys ou filmes de faroeste, compõe um gênero clássico do cinema norte-americano (ainda que outros países tenham produzido westerns, como aconteceu em Itália, com os seus western spaghetti)."},
        {"animação"},
        {"Animação refere-se ao processo segundo o qual cada fotograma de um filme é produzido individualmente, podendo ser gerado tanto por computação gráfica quanto fotografando uma imagem desenhada ou repetidamente fazendo-se pequenas mudanças a um modelo (ver claymation e stop motion), fotografando o resultado."},
        {"claymation"},
        {"Claymation ou clay animation é uma técnica de animação (stop motion) baseada em modelos de barro ou material similar."},
        {"stop", "motion"},
        {"Stop motion é uma técnica de animação fotograma a fotograma (ou quadro a quadro), usando como recurso uma máquina de filmar, uma máquina fotográfica ou um computador. Utilizam-se modelos reais em diversos materiais, os mais comuns são a massa de modelar ou massinha . Muitos contêm sistema de juntas mecânico, com mecanismos de articulações muito complexos. No cinema, o material utilizado tem de ser mais resistente e maleável, visto que os modelos precisam durar meses, pois para cada segundo de filme são necessárias aproximadamente 24 quadros (frames)."},
        {"guerra", "nas", "estrelas"},
        {"você gosta de guerra nas estrelas? poxa, que legal! quando o assunto é ficção científica, um dos primeiros filmes a vir á nossa cabeça é justamente guerra nas estrelas. O criador é George Lucas. Inicialmente ele criou o filme com um roteiro que daria 6 horas de filme, mas as produtoras recusaram, então ele resolveu separar os filmes por episódios"},
        {"star", "wars"},
        {"você gosta de guerra nas estrelas? poxa, que legal! quando o assunto é ficção científica, um dos primeiros filmes a vir á nossa cabeça é justamente guerra nas estrelas. O criador é George Lucas. Inicialmente ele criou o filme com um roteiro que daria 6 horas de filme, mas as produtoras recusaram, então ele resolveu separar os filmes por episódios"},
        {"gosta", "filmes"},
        {"se tem uma coisa que eu gosto é assistir filmes... os meus gêneros preferidos são romance, terror e suspense, e o seu?"},
        {"gosta", "guerra", "estrelas"},
        {"adoro !!! tenho os episódios IV, V e VI em DVD... meu personagem preferido é o Luke... George Lucas foi um gênio ao criar esses filmes"},
        {"gosta", "filme", "terror"},
        {"amo! já falei que é um dos meus gêneros de filme favoritos? um dos filmes de terror que eu mais gostei foi A Casa De Cera, tem um ótimo suspense... adoro filme de terror que tem um belo de um suspense, não que os outros filmes de terror não tenham suspense... na verdade todo filme de terror tem algum tipo de suspense embutido."},
        {"gosto", "harry", "potter"},
        {"olha que coincidência, eu também AMO Harry Potter, acho que a J.K. Rowling foi super original ao escrever essa história fantástica, quando eu vi o último filme então... eu mal conseguia respirar de tanta ansiedade"},
        {"gosto", "matrix"},
        {"eu também adoro!!! só vi o 1º filme, infelizmente, estou querendo muito ver os 2 últimos filmes... você assistiu todos?"},
        {"quero", "ser", "cineasta"},
        {"poxa, espero que você consiga! me chame quando você estiver com o seu nome na calçada da fama pra eu ir lá tirar foto"},
        {"gosto", "comédia", "romântica"},
        {"esse gênero de filme também está incluso na minha lista de gêneros de filmes preferidos... nada melhor do que ver um romance e rir né? eu gosto principalmente quando o casal protagonista no início se odeia, mas no fim acabam se entendendo depois de muita briga e comédia"},
        {"gosto", "filme", "ação"},
        {"ah, que pena, sinceramente, eu odeio filme de ação... sou mais os de terror, romance ou suspense..."},
        {"gosto", "ir", "cinema"},
        {"eu também gosto muito... aquele cheirinho de pipoca com manteiga que só nos cinemas têm... hmmmm... dá até água na boca... quem sabe eu não apareça nessa quarta no cinema já que o ingresso é mais barato... kkkkk"},
        {"gosto", "procurando", "nemo"},
        {"eu AMO esse filme!!! minha personagem preferida é a Dory. até me lembro de uma frase dela no meio do filme quando ela e o pai do Nemo foram engolidos pela baleia: ou ela disse pra descer pra garganta, ou ela disse que o Raimundo almoça e janta"},
        {"gosto", "monstros", "s.a"},
        {"ahhhhhh eu adorooooo o Mike (o monstrinho verde) ele é tão fofoooo!! você já assistiu o segundo filme? Universidade Monstros?"},
        {"assisti", "filme", "rio"},
        {"eu também já assisti... queria ver a continuação dele, Rio 2 que saiu no dia 27 de março nos cinemas... você já assistiu?"},
        {"assisti", "universidade", "monstros"},
        {"eu também... pena que não foi no cinema, foi em casa mesmo... preferia ver no cinema com aquele pipoca amanteigada que só de lembrar já me dá água na boca..."},
        {"cinemark"},
        {"a rede de cinemas cinemark é a rede mais fácil de achar pelos shoppings de São Paulo"},
        {"pipoca", "de", "cinema"},
        {"quando vou ao cinema não consigo ficar sem comprar um pacote de pipoca, nem que seja, pelo menos, o pequenininho... cinema sem pipoca, não é cinema, na minha opinião"},
        {"assisti", "filme", "3d"},
        {"eu também já assisti... uns 2 filmes, se não me engano, que foram Thor e a hora da escuridão (ou algo assim) gostei muito da experiência"},};

    public Conversa(Paciente paciente, Bot bot, Responsavel responsavel, Logs logs) {
        URL imageUrl = this.getClass().getResource("/Imagens/frameIcon.png");
        ImageIcon img = new ImageIcon(imageUrl);
        setIconImage(img.getImage());

        this.setPaciente(paciente);
        this.setBot(bot);
        this.setResponsavel(responsavel);
        this.setLogs(logs);
        setTitle("Janela de Conversa - " + getBot().getNomeBot());
        initComponents();
        setLocationRelativeTo(null);
        this.jTextFieldBot.setText(getBot().getNomeBot());
        this.jTextFieldPaciente.setText(getPaciente().getNomePaciente());
        dialog.setEditable(false);
        input.requestFocusInWindow();
        input.addKeyListener(this);
        //dialog.setLineWrap(true);
        dialog.setContentType("text/html");
        dialog.setEditorKit(new StyledEditorKit());
        dialog.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        dialog.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        //input.setLineWrap(true);

        input.setContentType("text/html");
        input.setEditorKit(new StyledEditorKit());
        input.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        input.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        //input.setWrapStyleWord(true);
        try {
            URL imageUrlF = this.getClass().getResource("/Emoticons/smile.png");
            smile = new ImageIcon(imageUrlF);
            URL imageUrlSur = this.getClass().getResource("/Emoticons/surprised.png");
            surprised = new ImageIcon(imageUrlSur);
            URL imageUrlSad = this.getClass().getResource("/Emoticons/sad.png");
            sad = new ImageIcon(imageUrlSad);
            URL imageUrlSun = this.getClass().getResource("/Emoticons/sunglasses.png");
            sunglasses = new ImageIcon(imageUrlSun);
            URL imageUrlSor = this.getClass().getResource("/Emoticons/sorry.png");
            sorry = new ImageIcon(imageUrlSor);
            URL imageUrlBli = this.getClass().getResource("/Emoticons/blink.png");
            blink = new ImageIcon(imageUrlBli);
            URL imageUrlHap = this.getClass().getResource("/Emoticons/grin.png");
            happy = new ImageIcon(imageUrlHap);
            URL imageUrlTon = this.getClass().getResource("/Emoticons/tongue.png");
            tongue = new ImageIcon(imageUrlTon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startRunning() {
        try {
            server = new ServerSocket(6789, 100);
            while (true) {
                try {
                    waitForConnection();
                    setupStreams();
                    whileChatting();
                } catch (Exception e) {
                    //showMessage("\n Server ended the connection");
                } finally {
                    closeCrap();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //wait for connection
    private void waitForConnection() throws IOException {
        //showMessage(" Waiting for someone to connect... \n");
        connection = server.accept();
        //showMessage(" FUNCIONOU " + connection.getInetAddress().getHostName());
    }

    //get stream to send and receive
    private void setupStreams() throws IOException {
        outputS = new ObjectOutputStream(connection.getOutputStream());
        outputS.flush();
        inputS = new ObjectInputStream(connection.getInputStream());
        //showMessage("\n Stream are now setup! \n");
    }

    //during the chat
    private void whileChatting() throws IOException {
        String message = "";
        //sendMessage(message);
        //ableToType(true);
        do {
            try {
                message = (String) inputS.readObject();
                message = message.replace("Você", getBot().getNomeBot());
                if (message.equals("DESATIVARBOTAGORA")) {
                    botAtivado = false;
                } else if (message.equals("ATIVARBOTAGORA")) {
                    botAtivado = true;
                } else {
                    showMessage(message + "\n\n");
                }
            } catch (ClassNotFoundException classNotFoundException) {
                //showMessage("\n idk wtf that user send!");
            }
        } while (!message.equals("CLIENT - END"));
    }

    //close streams and sockets
    private void closeCrap() {
        //showMessage("\n Closing connections... \n");
        //ableToType(false);
        try {
            outputS.close();
            inputS.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //send a message to client
    private void sendMessage(String message) {
        try {
            outputS.writeObject(message);
            outputS.flush();
            //showMessage("\nPACIENTE - " + message);
        } catch (Exception e) {
            //addText("\n ERROR: DUDE I CANT SEND THAT MESSAGE");
        }
    }

    //updates chatWindow
    private void showMessage(final String text) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        addText(text);
                        dialog.select(dialog.getText().length(), dialog.getText().length());
                    }
                }
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonBot = new javax.swing.JButton();
        jTextFieldBot = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButtonPaciente = new javax.swing.JButton();
        jTextFieldPaciente = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jButtonMudar = new javax.swing.JButton();
        jButtonEnviar = new javax.swing.JButton();
        jLabelDigitando = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        dialog = new javax.swing.JEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        input = new javax.swing.JEditorPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonBot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/male.gif"))); // NOI18N
        jButtonBot.setFocusable(false);

        jTextFieldBot.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldBot.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldBot, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonBot, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonBot, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldBot, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/male.gif"))); // NOI18N
        jButtonPaciente.setFocusable(false);

        jTextFieldPaciente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPaciente.setFocusable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPaciente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTextFieldPaciente, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButtonMudar.setText("Mudar foto");
        jButtonMudar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMudarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonMudar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonMudar)
                .addContainerGap())
        );

        jButtonEnviar.setText("Enviar");
        jButtonEnviar.setEnabled(false);
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        jLabelDigitando.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        dialog.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane3.setViewportView(dialog);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        input.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(input);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Emoticons/smile.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Emoticons/sad.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Emoticons/blink.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Emoticons/sorry.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Emoticons/grin.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Emoticons/sunglasses.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Emoticons/surprised.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Emoticons/tongue.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addComponent(jLabelDigitando, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabelDigitando, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jButtonEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initListener() {
        dialog.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent event) {
                final DocumentEvent e = event;
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (e.getDocument() instanceof StyledDocument) {
                            try {
                                StyledDocument doc = (StyledDocument) e.getDocument();
                                int start = Utilities.getRowStart(dialog, Math.max(0, e.getOffset() - 1));
                                int end = Utilities.getWordStart(dialog, e.getOffset() + e.getLength());
                                String text = doc.getText(start, end - start);

                                int i = text.indexOf(":)");
                                while (i >= 0) {
                                    final SimpleAttributeSet attrs = new SimpleAttributeSet(
                                            doc.getCharacterElement(start + i).getAttributes());
                                    if (StyleConstants.getIcon(attrs) == null) {
                                        StyleConstants.setIcon(attrs, smile);
                                        doc.remove(start + i, 2);
                                        doc.insertString(start + i, ":)", attrs);
                                    }
                                    i = text.indexOf(":)", i + 2);
                                }

                                int o = text.indexOf(":o");
                                while (o >= 0) {
                                    final SimpleAttributeSet attrs = new SimpleAttributeSet(
                                            doc.getCharacterElement(start + o).getAttributes());
                                    if (StyleConstants.getIcon(attrs) == null) {
                                        StyleConstants.setIcon(attrs, surprised);
                                        doc.remove(start + o, 2);
                                        doc.insertString(start + o, ":o", attrs);
                                    }
                                    o = text.indexOf(":o", o + 2);
                                }

                                int t = text.indexOf(":(");
                                while (t >= 0) {
                                    final SimpleAttributeSet attrs = new SimpleAttributeSet(
                                            doc.getCharacterElement(start + t).getAttributes());
                                    if (StyleConstants.getIcon(attrs) == null) {
                                        StyleConstants.setIcon(attrs, sad);
                                        doc.remove(start + t, 2);
                                        doc.insertString(start + t, ":(", attrs);
                                    }
                                    t = text.indexOf(":(", t + 2);
                                }

                                int c = text.indexOf("8)");
                                while (c >= 0) {
                                    final SimpleAttributeSet attrs = new SimpleAttributeSet(
                                            doc.getCharacterElement(start + c).getAttributes());
                                    if (StyleConstants.getIcon(attrs) == null) {
                                        StyleConstants.setIcon(attrs, sunglasses);
                                        doc.remove(start + c, 2);
                                        doc.insertString(start + c, "8)", attrs);
                                    }
                                    c = text.indexOf("8)", c + 2);
                                }

                                int y = text.indexOf(":/");
                                while (y >= 0) {
                                    final SimpleAttributeSet attrs = new SimpleAttributeSet(
                                            doc.getCharacterElement(start + y).getAttributes());
                                    if (StyleConstants.getIcon(attrs) == null) {
                                        StyleConstants.setIcon(attrs, sorry);
                                        doc.remove(start + y, 2);
                                        doc.insertString(start + y, ":/", attrs);
                                    }
                                    y = text.indexOf(":/", y + 2);
                                }

                                int p = text.indexOf(";)");
                                while (p >= 0) {
                                    final SimpleAttributeSet attrs = new SimpleAttributeSet(
                                            doc.getCharacterElement(start + p).getAttributes());
                                    if (StyleConstants.getIcon(attrs) == null) {
                                        StyleConstants.setIcon(attrs, blink);
                                        doc.remove(start + p, 2);
                                        doc.insertString(start + p, ";)", attrs);
                                    }
                                    p = text.indexOf(";)", p + 2);
                                }

                                int g = text.indexOf("=D");
                                while (g >= 0) {
                                    final SimpleAttributeSet attrs = new SimpleAttributeSet(
                                            doc.getCharacterElement(start + g).getAttributes());
                                    if (StyleConstants.getIcon(attrs) == null) {
                                        StyleConstants.setIcon(attrs, happy);
                                        doc.remove(start + g, 2);
                                        doc.insertString(start + g, "=D", attrs);
                                    }
                                    g = text.indexOf("=D", g + 2);
                                }

                                int v = text.indexOf(":p");
                                while (v >= 0) {
                                    final SimpleAttributeSet attrs = new SimpleAttributeSet(
                                            doc.getCharacterElement(start + v).getAttributes());
                                    if (StyleConstants.getIcon(attrs) == null) {
                                        StyleConstants.setIcon(attrs, tongue);
                                        doc.remove(start + v, 2);
                                        doc.insertString(start + v, ":p", attrs);
                                    }
                                    v = text.indexOf(":p", v + 2);
                                }
                            } catch (BadLocationException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
            }

            public void removeUpdate(DocumentEvent e) {
            }

            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed
        try {
            this.jButtonEnviar.setEnabled(false);
            if (new File(getLogs().getEnderecoPaciente()).exists()) {
                if (!input.getText().equals("")) {
                    input.setEditable(true);
                    msg = input.getText();
                    input.setText("");
                    sendMessage(msg);
                    initListener();
                    String msgLow = msg.toLowerCase();
                    addText("   " + getPaciente().getNomePaciente() + ": " + msg + "\n");
                    addText("\n");

                    if (!respondido) {
                        msgOld = msg;
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        try {
                                            if (msgOld.equals(msg)) {
                                                digitando();
                                                new java.util.Timer().schedule(
                                                        new java.util.TimerTask() {
                                                            @Override
                                                            public void run() {
                                                                try {
                                                                    if (!brincando) {
                                                                        jLabelDigitando.setText("");
                                                                        InputStream in = Main.class.getResourceAsStream("/Sons/Msg.wav");
                                                                        InputStream bufferedIn = new BufferedInputStream(in);
                                                                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                                                                        clip = AudioSystem.getClip();
                                                                        clip.open(audioStream);
                                                                        clip.start();
                                                                        Thread.sleep(1000);
                                                                        addText("   " + getBot().getNomeBot() + ": " + perguntas[rand1.nextInt(((9 - 0) + 1) + 0)] + "\n");
                                                                        sendMessage("   " + getBot().getNomeBot() + ": " + perguntas[rand1.nextInt(((9 - 0) + 1) + 0)] + "\n");
                                                                        addText("\n");
                                                                        dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                        falando = false;
                                                                    }
                                                                } catch (Exception e) {
                                                                    JOptionPane.showMessageDialog(rootPane, e);
                                                                }
                                                            }
                                                        },
                                                        rand1.nextInt((7500 - 4800) + 1) + 4800
                                                );

                                            }
                                            respondido = false;
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                60000
                        );
                        respondido = true;
                    }

                    if (!falando) {
                        respBot = 0;
                        int resposta = 0;
                        int i = 0;
                        while (resposta == 0 && botAtivado) {
                            for (i = 0; i < chatBot.length - 1; i += 2) {
                                if (chatBot[i].length == 3) {
                                    if (msgLow.contains(chatBot[i][0]) && msgLow.contains(chatBot[i][1]) && msgLow.contains(chatBot[i][2])) {
                                        respBot = 1;
                                        ilol = i;
                                    }
                                }
                                if (chatBot[i].length == 2) {
                                    if (msgLow.contains(chatBot[i][0]) && msgLow.contains(chatBot[i][1])) {
                                        respBot = 1;
                                        ilol = i;
                                    }
                                }
                                if (chatBot[i].length == 1) {
                                    if (msgLow.contains(chatBot[i][0])) {
                                        respBot = 1;
                                        ilol = i;
                                    }
                                }
                            }
                            if (i >= chatBot.length - 1) {
                                if (respBot == 1) {
                                    digitando();
                                    digitou();
                                    resposta = 1;
                                } else {
                                    resposta = 2;
                                }
                            }
                        }
                        if (resposta == 2) {
                            if (msg.equals(this.getResponsavel().getSenhaResponsavel())) {
                                FileInputStream fstream = new FileInputStream(getLogs().getEnderecoPaciente());
                                DataInputStream in = new DataInputStream(fstream);
                                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                                String strLine;
                                String texto = "";
                                while ((strLine = br.readLine()) != null) {
                                    if (strLine.contains("\n")) {
                                        texto += strLine;
                                    } else {
                                        texto += strLine + "\n";
                                    }
                                }
                                in.close();

                                File file = new File(getLogs().getEnderecoPaciente());
                                file.createNewFile();
                                BufferedWriter output = new BufferedWriter(new FileWriter(file));

                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                Date date = new Date();

                                output.write(texto + dateFormat.format(date) + "\n" + this.getResponsavel().getNomeResponsavel() + " entrou na Configuração." + "\n\n");

                                output.close();

                                Admin admin = new Admin();
                                admin.setVisible(true);
                                dispose();
                            } else {
                                this.digitando();
                                new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                            @Override
                                            public void run() {
                                                try {
                                                    jLabelDigitando.setText("");
                                                    InputStream in = Main.class.getResourceAsStream("/Sons/Msg.wav");
                                                    InputStream bufferedIn = new BufferedInputStream(in);
                                                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                                                    clip = AudioSystem.getClip();
                                                    clip.open(audioStream);
                                                    clip.start();

                                                    Thread.sleep(1000);

                                                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/autismo", "root", "");
                                                    java.sql.Statement stmtp = connection.createStatement();
                                                    String sqlp = "SELECT id FROM estatistica";
                                                    ResultSet rsp = stmtp.executeQuery(sqlp);

                                                    Estatistica estatistica = new Estatistica();

                                                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                                    String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
                                                    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
                                                    String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                                                    String dateLog = day + "/" + month + "/" + year;

                                                    Date date = formatter.parse(dateLog);

                                                    rsp.last();
                                                    while (!encontradoEstatistica && !rsp.isBeforeFirst()) {
                                                        Long idr = rsp.getLong("id");
                                                        estatistica.encontradoId(idr);

                                                        if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                                            encontradoEstatistica = true;
                                                        }

                                                        if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                                            encontradoEstatistica = true;
                                                        }
                                                        rsp.previous();
                                                    }

                                                    encontradoEstatistica = false;

                                                    ++respostasNum;
                                                    if (respostasNum != 3) {
                                                        addText("   " + getBot().getNomeBot() + ": " + fugas[rand1.nextInt(((4 - 0) + 1) + 0)] + "\n");
                                                        sendMessage("   " + getBot().getNomeBot() + ": " + fugas[rand1.nextInt(((4 - 0) + 1) + 0)] + "\n");
                                                        addText("\n");
                                                        dialog.select(dialog.getText().length(), dialog.getText().length());
                                                        falando = false;
                                                    } else {
                                                        Calendar a = Calendar.getInstance(Locale.FRENCH);
                                                        a.setTime(getPaciente().getIdadePaciente());
                                                        Calendar b = Calendar.getInstance(Locale.FRENCH);
                                                        b.setTime(Calendar.getInstance().getTime());

                                                        int diff = b.get(YEAR) - a.get(YEAR);
                                                        if (a.get(MONTH) > b.get(MONTH) || (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
                                                            diff--;
                                                        }

                                                        if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                                            if (diff >= 10 && diff <= 14 && estatistica.getNota1() == null) {
                                                                addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                addText("\n");
                                                                dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                falando = false;
                                                                checkMicrophoneSapo();
                                                            } else if (diff >= 15 && diff <= 20 && estatistica.getNota2() == null) {
                                                                addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos praticar uma atividade?\n");
                                                                sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos praticar uma atividade?\n");
                                                                addText("\n");
                                                                dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                falando = false;
                                                                checkMicrophoneAud();
                                                            } else if (diff >= 6 && diff <= 9 && estatistica.getNota3() == null) {
                                                                addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                addText("\n");
                                                                dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                falando = false;
                                                                checkAma();
                                                            } else {
                                                                addText("   " + getBot().getNomeBot() + ": " + fugas[rand1.nextInt(((4 - 0) + 1) + 0)] + "\n");
                                                                sendMessage("   " + getBot().getNomeBot() + ": " + fugas[rand1.nextInt(((4 - 0) + 1) + 0)] + "\n");
                                                                addText("\n");
                                                                dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                falando = false;
                                                            }
                                                        } else if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                                            if (diff >= 10 && diff <= 14) {
                                                                addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                addText("\n");
                                                                dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                falando = false;
                                                                checkMicrophoneSapo();
                                                            } else if (diff >= 15 && diff <= 20) {
                                                                addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos praticar uma atividade?\n");
                                                                sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos praticar uma atividade?\n");
                                                                addText("\n");
                                                                dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                falando = false;
                                                                checkMicrophoneAud();
                                                            } else if (diff >= 6 && diff <= 9) {
                                                                addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                addText("\n");
                                                                dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                falando = false;
                                                                checkAma();
                                                            } else {
                                                                addText("   " + getBot().getNomeBot() + ": " + fugas[rand1.nextInt(((4 - 0) + 1) + 0)] + "\n");
                                                                sendMessage("   " + getBot().getNomeBot() + ": " + fugas[rand1.nextInt(((4 - 0) + 1) + 0)] + "\n");
                                                                addText("\n");
                                                                dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                falando = false;
                                                            }
                                                        }
                                                    }
                                                } catch (Exception e) {
                                                    JOptionPane.showMessageDialog(rootPane, e);
                                                }
                                            }
                                        },
                                        rand1
                                        .nextInt(
                                                (7500 - 4800) + 1) + 4800
                                );
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Digite alguma coisa antes de enviar", "Mensagem", 1);
                }
            } else if (input.getText().equals(this.getResponsavel().getSenhaResponsavel())) {
                Admin admin = new Admin();
                admin.setVisible(true);
                dispose();

            } else {
                input.setEditable(false);
                JOptionPane.showMessageDialog(null, getBot().getNomeBot() + " não consegue falar com você.", "Comunicação", 1);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_jButtonEnviarActionPerformed

    public void checkMicrophoneAud() {
        String[] options = new String[2];
        options[0] = new String("Sim");
        options[1] = new String("Não");

        String[] options1 = new String[2];
        options1[0] = new String("Conectei!");
        options1[1] = new String("Não quero mais praticar");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/autismo", "root", "");
            java.sql.Statement stmtp = connection.createStatement();
            String sqlp = "SELECT id FROM estatistica";
            ResultSet rsp = stmtp.executeQuery(sqlp);

            Estatistica estatistica = new Estatistica();
            Estatistica estatistica1 = new Estatistica();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
            String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            String dateLog = day + "/" + month + "/" + year;

            Date date = formatter.parse(dateLog);

            rsp.last();
            while (!encontradoEstatistica && !rsp.isBeforeFirst()) {
                Long idr = rsp.getLong("id");
                estatistica.encontradoId(idr);

                if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                    estatistica1.setIdPaciente(getPaciente());
                    estatistica1.setData(formatter.parse(dateLog));
                    estatistica1.setNomePaciente(getPaciente().getNomePaciente());
                    estatistica1.setIdBot(getBot());
                    estatistica1.setNomeBot(getBot().getNomeBot());
                    estatistica1.armazenado();
                    encontradoEstatistica = true;
                }

                if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                    encontradoEstatistica = true;
                }
                rsp.previous();
            }

            if (brincar) {
                if (!AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
                    if (JOptionPane.showOptionDialog(null, "Microfone não conectado!", "Pergunta", 0, JOptionPane.INFORMATION_MESSAGE, null, options1, null) == JOptionPane.OK_OPTION) {
                        checkMicrophoneSapo();
                    } else {
                        digitando();

                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public
                                    void run() {
                                        try {
                                            InputStream in = Main.class.getResourceAsStream("/Sons/Msg.wav");
                                            InputStream bufferedIn = new BufferedInputStream(in);
                                            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                                            clip = AudioSystem.getClip();
                                            clip.open(audioStream);
                                            clip.start();

                                            Thread.sleep(1000);
                                            jLabelDigitando.setText(
                                                    "");
                                            addText(
                                                    "   " + getBot().getNomeBot() + ": " + "Sem problemas, vamos continuar conversando normalmente então...\n");
                                            sendMessage(
                                                    "   " + getBot().getNomeBot() + ": " + "Sem problemas, vamos continuar conversando normalmente então...\n");
                                            addText(
                                                    "\n");
                                            falando = false;

                                            dialog.select(dialog.getText().length(), dialog.getText().length());
                                        } catch (Exception e) {
                                            JOptionPane.showMessageDialog(rootPane, e);
                                        }
                                    }
                                },
                                rand1.nextInt((7500 - 4800) + 1) + 4800
                        );

                        if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                            estatistica1.setNota2(0.0);
                            estatistica1.atualizado();
                        }
                        if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                            estatistica.setNota2(0.0);
                            estatistica.atualizado();
                        }
                    }
                }
                if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
                    if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                        AudFrame audFrame = new AudFrame(getPaciente(), estatistica1);
                        brincando = true;
                    }

                    if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                        AudFrame audFrame = new AudFrame(getPaciente(), estatistica);
                        brincando = true;
                    }

                    /*addText("   " + getBot().getNomeBot() + ": " + "Vamos praticar então!\n");
                     sendMessage("   " + getBot().getNomeBot() + ": " + "Vamos praticar então!\n");
                     addText("\n");
                     dialog.select(dialog.getText().length(), dialog.getText().length());*/
                }
            }

            if (!brincar) {
                if (JOptionPane.showOptionDialog(null, "Quer praticar algo comigo?\nVocê precisa de um microfone conectado no Computador", "Pergunta", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null) == JOptionPane.OK_OPTION) {
                    brincar = true;
                    if (!AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
                        if (JOptionPane.showOptionDialog(null, "Microfone não conectado!", "Pergunta", 0, JOptionPane.INFORMATION_MESSAGE, null, options1, null) == JOptionPane.OK_OPTION) {
                            checkMicrophoneSapo();
                        } else {
                            digitando();

                            new java.util.Timer().schedule(
                                    new java.util.TimerTask() {
                                        @Override
                                        public
                                        void run() {
                                            try {
                                                InputStream in = Main.class.getResourceAsStream("/Sons/Msg.wav");
                                                InputStream bufferedIn = new BufferedInputStream(in);
                                                AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                                                clip = AudioSystem.getClip();
                                                clip.open(audioStream);
                                                clip.start();
                                                Thread.sleep(
                                                        1000);
                                                jLabelDigitando.setText(
                                                        "");
                                                addText(
                                                        "   " + getBot().getNomeBot() + ": " + "Sem problemas, vamos continuar conversando normalmente então...\n");
                                                sendMessage(
                                                        "   " + getBot().getNomeBot() + ": " + "Sem problemas, vamos continuar conversando normalmente então...\n");
                                                addText(
                                                        "\n");
                                                falando = false;

                                                dialog.select(dialog.getText().length(), dialog.getText().length());
                                            } catch (Exception e) {
                                                JOptionPane.showMessageDialog(rootPane, e);
                                            }
                                        }
                                    },
                                    rand1.nextInt((7500 - 4800) + 1) + 4800
                            );

                            if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                estatistica1.setNota2(0.0);
                                estatistica1.atualizado();
                            }
                            if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                estatistica.setNota2(0.0);
                                estatistica.atualizado();
                            }
                        }
                    }
                    if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {

                        if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                            AudFrame audFrame = new AudFrame(getPaciente(), estatistica1);
                            brincando = true;
                        }

                        if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                            AudFrame audFrame = new AudFrame(getPaciente(), estatistica);
                            brincando = true;
                        }

                        /*addText("   " + getBot().getNomeBot() + ": " + "Vamos praticar então!\n");
                         sendMessage("   " + getBot().getNomeBot() + ": " + "Vamos praticar então!\n");
                         addText("\n");
                         dialog.select(dialog.getText().length(), dialog.getText().length());*/
                    }
                } else {
                    digitando();

                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public
                                void run() {
                                    try {
                                        InputStream in = Main.class.getResourceAsStream("/Sons/Msg.wav");
                                        InputStream bufferedIn = new BufferedInputStream(in);
                                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                                        clip = AudioSystem.getClip();
                                        clip.open(audioStream);
                                        clip.start();

                                        Thread.sleep(
                                                1000);
                                        jLabelDigitando.setText(
                                                "");
                                        addText(
                                                "   " + getBot().getNomeBot() + ": " + "Sem problemas, vamos continuar conversando normalmente então...\n");
                                        sendMessage(
                                                "   " + getBot().getNomeBot() + ": " + "Sem problemas, vamos continuar conversando normalmente então...\n");
                                        addText(
                                                "\n");
                                        falando = false;

                                        dialog.select(dialog.getText().length(), dialog.getText().length());
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(rootPane, e);
                                    }
                                }
                            },
                            rand1.nextInt((7500 - 4800) + 1) + 4800
                    );

                    if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                        estatistica1.setNota2(0.0);
                        estatistica1.atualizado();
                    }
                    if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                        estatistica.setNota2(0.0);
                        estatistica.atualizado();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkAma() {
        String[] options = new String[2];
        options[0] = new String("Sim");
        options[1] = new String("Não");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/autismo", "root", "");
            java.sql.Statement stmtp = connection.createStatement();
            String sqlp = "SELECT id FROM estatistica";
            ResultSet rsp = stmtp.executeQuery(sqlp);

            Estatistica estatistica = new Estatistica();
            Estatistica estatistica1 = new Estatistica();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
            String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            String dateLog = day + "/" + month + "/" + year;

            Date date = formatter.parse(dateLog);

            rsp.last();
            while (!encontradoEstatistica && !rsp.isBeforeFirst()) {
                Long idr = rsp.getLong("id");
                estatistica.encontradoId(idr);

                if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                    estatistica1.setIdPaciente(getPaciente());
                    estatistica1.setData(formatter.parse(dateLog));
                    estatistica1.setNomePaciente(getPaciente().getNomePaciente());
                    estatistica1.setIdBot(getBot());
                    estatistica1.setNomeBot(getBot().getNomeBot());
                    estatistica1.armazenado();
                    encontradoEstatistica = true;
                }

                if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                    encontradoEstatistica = true;
                }
                rsp.previous();
            }

            if (JOptionPane.showOptionDialog(null, "Quer brincar comigo?", "Pergunta", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null) == JOptionPane.OK_OPTION) {
                if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                    AtvOpc atvOpc = new AtvOpc(estatistica1);
                    atvOpc.setVisible(true);
                    brincando = true;
                }

                if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                    AtvOpc atvOpc = new AtvOpc(estatistica);
                    atvOpc.setVisible(true);
                    brincando = true;
                }

            } else {
                digitando();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public
                            void run() {
                                try {
                                    InputStream in = Main.class.getResourceAsStream("/Sons/Msg.wav");
                                    InputStream bufferedIn = new BufferedInputStream(in);
                                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                                    clip = AudioSystem.getClip();
                                    clip.open(audioStream);
                                    clip.start();
                                    Thread.sleep(1000);
                                    jLabelDigitando.setText("");
                                    addText("   " + getBot().getNomeBot() + ": " + "Sem problemas, vamos continuar conversando normalmente então...\n");
                                    sendMessage("   " + getBot().getNomeBot() + ": " + "Sem problemas, vamos continuar conversando normalmente então...\n");
                                    addText("\n");
                                    falando = false;
                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(rootPane, e);
                                }
                            }
                        },
                        rand1.nextInt((7500 - 4800) + 1) + 4800
                );

                if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                    estatistica1.setNota3(0.0);
                    estatistica1.atualizado();
                }
                if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                    estatistica.setNota3(0.0);
                    estatistica.atualizado();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkMicrophoneSapo() {
        String[] options = new String[2];
        options[0] = new String("Sim");
        options[1] = new String("Não");

        String[] options1 = new String[2];
        options1[0] = new String("Conectei!");
        options1[1] = new String("Não quero mais brincar");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/autismo", "root", "");
            java.sql.Statement stmtp = connection.createStatement();
            String sqlp = "SELECT id FROM estatistica";
            ResultSet rsp = stmtp.executeQuery(sqlp);

            Estatistica estatistica = new Estatistica();
            Estatistica estatistica1 = new Estatistica();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
            String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            String dateLog = day + "/" + month + "/" + year;

            Date date = formatter.parse(dateLog);

            rsp.last();
            while (!encontradoEstatistica && !rsp.isBeforeFirst()) {
                Long idr = rsp.getLong("id");
                estatistica.encontradoId(idr);

                if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                    estatistica1.setIdPaciente(getPaciente());
                    estatistica1.setData(formatter.parse(dateLog));
                    estatistica1.setNomePaciente(getPaciente().getNomePaciente());
                    estatistica1.setIdBot(getBot());
                    estatistica1.setNomeBot(getBot().getNomeBot());
                    estatistica1.armazenado();
                    encontradoEstatistica = true;
                }

                if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                    encontradoEstatistica = true;
                }
                rsp.previous();
            }

            if (brincar) {
                if (!AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
                    if (JOptionPane.showOptionDialog(null, "Microfone não conectado!", "Pergunta", 0, JOptionPane.INFORMATION_MESSAGE, null, options1, null) == JOptionPane.OK_OPTION) {
                        checkMicrophoneSapo();
                    } else {
                        digitando();

                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public
                                    void run() {
                                        try {
                                            InputStream in = Main.class.getResourceAsStream("/Sons/Msg.wav");
                                            InputStream bufferedIn = new BufferedInputStream(in);
                                            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                                            clip = AudioSystem.getClip();
                                            clip.open(audioStream);
                                            clip.start();

                                            Thread.sleep(
                                                    1000);
                                            jLabelDigitando.setText(
                                                    "");
                                            addText(
                                                    "   " + getBot().getNomeBot() + ": " + "Sem problemas, vamos continuar conversando normalmente então...\n");
                                            sendMessage(
                                                    "   " + getBot().getNomeBot() + ": " + "Sem problemas, vamos continuar conversando normalmente então...\n");
                                            addText(
                                                    "\n");
                                            falando = false;

                                            dialog.select(dialog.getText().length(), dialog.getText().length());
                                        } catch (Exception e) {
                                            JOptionPane.showMessageDialog(rootPane, e);
                                        }
                                    }
                                },
                                rand1.nextInt((7500 - 4800) + 1) + 4800
                        );
                        if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                            estatistica1.setNota1(0.0);
                            estatistica1.atualizado();
                        }
                        if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                            estatistica.setNota1(0.0);
                            estatistica.atualizado();
                        }
                    }
                }
                if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
                    String[] args = {};
                    if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                        new GameFrame(estatistica1).mainA(args);
                        brincando = true;
                    }
                    if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                        new GameFrame(estatistica).mainA(args);
                        brincando = true;
                    }

                    /*addText(
                     "   " + getBot().getNomeBot() + ": " + "Vamos brincar então!\n");
                     sendMessage(
                     "   " + getBot().getNomeBot() + ": " + "Vamos brincar então!\n");
                     addText(
                     "\n");
                     dialog.select(dialog.getText().length(), dialog.getText().length());*/
                }
            }

            if (!brincar) {
                if (JOptionPane.showOptionDialog(null, "Quer brincar comigo?\nVocê precisa de um microfone conectado no Computador", "Pergunta", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null) == JOptionPane.OK_OPTION) {
                    brincar = true;
                    if (!AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
                        if (JOptionPane.showOptionDialog(null, "Microfone não conectado!", "Pergunta", 0, JOptionPane.INFORMATION_MESSAGE, null, options1, null) == JOptionPane.OK_OPTION) {
                            checkMicrophoneSapo();
                        } else {
                            digitando();

                            new java.util.Timer().schedule(
                                    new java.util.TimerTask() {
                                        @Override
                                        public
                                        void run() {
                                            try {
                                                InputStream in = Main.class.getResourceAsStream("/Sons/Msg.wav");
                                                InputStream bufferedIn = new BufferedInputStream(in);
                                                AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                                                clip = AudioSystem.getClip();
                                                clip.open(audioStream);
                                                clip.start();

                                                Thread.sleep(
                                                        1000);
                                                jLabelDigitando.setText(
                                                        "");
                                                addText(
                                                        "   " + getBot().getNomeBot() + ": " + "Sem problemas, vamos continuar conversando normalmente então...\n");
                                                sendMessage(
                                                        "   " + getBot().getNomeBot() + ": " + "Sem problemas, vamos continuar conversando normalmente então...\n");
                                                addText(
                                                        "\n");
                                                falando = false;

                                                dialog.select(dialog.getText().length(), dialog.getText().length());
                                            } catch (Exception e) {
                                                JOptionPane.showMessageDialog(rootPane, e);
                                            }
                                        }
                                    },
                                    rand1.nextInt((7500 - 4800) + 1) + 4800
                            );
                            if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                estatistica1.setNota1(0.0);
                                estatistica1.atualizado();
                            }
                            if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                estatistica.setNota1(0.0);
                                estatistica.atualizado();
                            }
                        }
                    }
                    if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
                        String[] args = {};
                        if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                            new GameFrame(estatistica1).mainA(args);
                            brincando = true;
                        }
                        if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                            new GameFrame(estatistica).mainA(args);
                            brincando = true;

                        }

                        /*addText(
                         "   " + getBot().getNomeBot() + ": " + "Vamos brincar então!\n");
                         sendMessage(
                         "   " + getBot().getNomeBot() + ": " + "Vamos brincar então!\n");
                         addText(
                         "\n");
                         dialog.select(dialog.getText().length(), dialog.getText().length());*/
                    }
                } else {
                    digitando();

                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public
                                void run() {
                                    try {
                                        InputStream in = Main.class.getResourceAsStream("/Sons/Msg.wav");
                                        InputStream bufferedIn = new BufferedInputStream(in);
                                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                                        clip = AudioSystem.getClip();
                                        clip.open(audioStream);
                                        clip.start();

                                        Thread.sleep(
                                                1000);
                                        jLabelDigitando.setText(
                                                "");
                                        addText(
                                                "   " + getBot().getNomeBot() + ": " + "Sem problemas, vamos continuar conversando normalmente então...\n");
                                        sendMessage(
                                                "   " + getBot().getNomeBot() + ": " + "Sem problemas, vamos continuar conversando normalmente então...\n");
                                        addText(
                                                "\n");
                                        falando = false;

                                        dialog.select(dialog.getText().length(), dialog.getText().length());
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(rootPane, e);
                                    }
                                }
                            },
                            rand1.nextInt((7500 - 4800) + 1) + 4800
                    );
                    if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                        estatistica1.setNota1(0.0);
                        estatistica1.atualizado();
                    }
                    if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                        estatistica.setNota1(0.0);
                        estatistica.atualizado();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            if (new File(getLogs().getEnderecoPaciente()).exists()) {
                FileInputStream fstream = new FileInputStream(getLogs().getEnderecoPaciente());
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                String texto = "";
                while ((strLine = br.readLine()) != null) {
                    if (strLine.contains("\n")) {
                        texto += strLine;
                    } else {
                        texto += strLine + "\n";
                    }
                }
                in.close();

                File file = new File(getLogs().getEnderecoPaciente());
                file.createNewFile();
                BufferedWriter output = new BufferedWriter(new FileWriter(file));

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();

                if (dialog.getText().equals("")) {
                    output.write(texto + dateFormat.format(date) + "\n" + "Paciente não escreveu nada" + "\n\n");
                } else {
                    output.write(texto + dateFormat.format(date) + "\n" + this.dialog.getText() + "\n");
                }
                output.close();
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //System.out.println(chatBot.length);
        Calendar a = Calendar.getInstance(Locale.FRENCH);
        a.setTime(getPaciente().getIdadePaciente());
        Calendar b = Calendar.getInstance(Locale.FRENCH);
        b.setTime(Calendar.getInstance().getTime());

        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) || (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }

        idade = diff;

        if (getBot().getSexoBot().equals("Feminino")) {
            URL imageUrl = this.getClass().getResource("/Imagens/female.jpg");
            ImageIcon icon = new ImageIcon(imageUrl);
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(this.jButtonBot.getWidth(), this.jButtonBot.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            this.jButtonBot.setIcon(icon);
        }
        if (getPaciente().getSexoPaciente().equals("Feminino")) {
            URL imageUrl = this.getClass().getResource("/Imagens/female.jpg");
            ImageIcon icon = new ImageIcon(imageUrl);
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(this.jButtonPaciente.getWidth(), this.jButtonPaciente.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            this.jButtonPaciente.setIcon(icon);
        }
        if (getPaciente().getFotoPaciente() != null && new File(getPaciente().getFotoPaciente()).exists()) {
            ImageIcon icon = new ImageIcon(getPaciente().getFotoPaciente());
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(this.jButtonPaciente.getWidth(), this.jButtonPaciente.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            this.jButtonPaciente.setIcon(icon);
        }

        initListener();

        falando = true;
        digitando();

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        try {
                            jLabelDigitando.setText("");
                            InputStream in = Main.class.getResourceAsStream("/Sons/Msg.wav");
                            InputStream bufferedIn = new BufferedInputStream(in);
                            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                            clip = AudioSystem.getClip();
                            clip.open(audioStream);
                            clip.start();
                            Thread.sleep(1000);
                            addText("   " + getBot().getNomeBot() + ": " + boas[rand1.nextInt(((4 - 0) + 1) + 0)] + "\n");
                            sendMessage("   " + getBot().getNomeBot() + ": " + boas[rand1.nextInt(((4 - 0) + 1) + 0)] + "\n");
                            addText("\n");
                            falando = false;
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(rootPane, e);
                        }
                    }
                },
                rand1.nextInt((7500 - 4800) + 1) + 4800
        );
    }//GEN-LAST:event_formWindowOpened

    private void jButtonMudarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMudarActionPerformed
        String oldImg = getPaciente().getFotoPaciente();
        BufferedImage image;
        try {
            String userhome = System.getProperty("user.home");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image file only", "jpg", "jpeg", "bmp", "png");
            JFileChooser fileChooser = new JFileChooser(userhome + "\\Pictures");
            fileChooser.setFileFilter(filter);
            fileChooser.setAcceptAllFileFilterUsed(false);
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {

                File file = fileChooser.getSelectedFile();

                if (getPaciente().getFotoPaciente() != null) {
                    File f1 = new File(oldImg);
                    f1.delete();
                }

                image = ImageIO.read(file);
                Image newimg = image.getScaledInstance(this.jButtonPaciente.getWidth(), this.jButtonPaciente.getHeight(), java.awt.Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(newimg);
                this.jButtonPaciente.setIcon(icon);
                String str = file.getAbsolutePath();
                str = str.replace("\\", "/");
                File filesv = new File(str);
                File destinationDir = new File("C:/SheldonChat/Imagens");
                FileUtils.copyFileToDirectory(filesv, destinationDir);
                String strLast = str.substring(str.lastIndexOf("/") + 1);
                String ext = strLast.substring(strLast.lastIndexOf(".") + 1);
                String nomeFinal = "C:/SheldonChat/Imagens/" + getPaciente().getNomePaciente() + "Foto." + ext;
                File oldf = new File("C:/SheldonChat/Imagens/" + strLast);
                File newf = new File(nomeFinal);
                oldf.renameTo(newf);

                getPaciente().setFotoPaciente(newf.getAbsolutePath());
                getPaciente().atualizado();

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButtonMudarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        input.setText(input.getText() + ":)");
        if (!input.getText().equals("")) {
            this.jButtonEnviar.setEnabled(true);
        } else {
            this.jButtonEnviar.setEnabled(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        input.setText(input.getText() + ":(");
        if (!input.getText().equals("")) {
            this.jButtonEnviar.setEnabled(true);
        } else {
            this.jButtonEnviar.setEnabled(false);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        input.setText(input.getText() + ";)");
        if (!input.getText().equals("")) {
            this.jButtonEnviar.setEnabled(true);
        } else {
            this.jButtonEnviar.setEnabled(false);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        input.setText(input.getText() + ":/");
        if (!input.getText().equals("")) {
            this.jButtonEnviar.setEnabled(true);
        } else {
            this.jButtonEnviar.setEnabled(false);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        input.setText(input.getText() + "=D");
        if (!input.getText().equals("")) {
            this.jButtonEnviar.setEnabled(true);
        } else {
            this.jButtonEnviar.setEnabled(false);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        input.setText(input.getText() + "8)");
        if (!input.getText().equals("")) {
            this.jButtonEnviar.setEnabled(true);
        } else {
            this.jButtonEnviar.setEnabled(false);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        input.setText(input.getText() + ":o");
        if (!input.getText().equals("")) {
            this.jButtonEnviar.setEnabled(true);
        } else {
            this.jButtonEnviar.setEnabled(false);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        input.setText(input.getText() + ":p");
        if (!input.getText().equals("")) {
            this.jButtonEnviar.setEnabled(true);
        } else {
            this.jButtonEnviar.setEnabled(false);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    public void keyPressed(KeyEvent e) {
        try {
            if (!input.getText().equals("")) {
                this.jButtonEnviar.setEnabled(true);
            } else {
                this.jButtonEnviar.setEnabled(false);
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (new File(getLogs().getEnderecoPaciente()).exists()) {
                    if (!input.getText().equals("")) {
                        input.setEditable(false);
                        msg = input.getText();
                        input.setText("");
                        initListener();
                        String msgLow = msg.toLowerCase();
                        addText("   " + getPaciente().getNomePaciente() + ": " + msg + "\n");
                        sendMessage("   " + getPaciente().getNomePaciente() + ": " + msg + "\n");
                        addText("\n");

                        if (!respondido) {
                            msgOld = msg;
                            new java.util.Timer().schedule(
                                    new java.util.TimerTask() {
                                        @Override
                                        public void run() {
                                            try {
                                                if (msgOld.equals(msg)) {
                                                    digitando();
                                                    new java.util.Timer().schedule(
                                                            new java.util.TimerTask() {
                                                                @Override
                                                                public void run() {
                                                                    try {
                                                                        if (!brincando) {
                                                                            jLabelDigitando.setText("");
                                                                            InputStream in = Main.class.getResourceAsStream("/Sons/Msg.wav");
                                                                            InputStream bufferedIn = new BufferedInputStream(in);
                                                                            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                                                                            clip = AudioSystem.getClip();
                                                                            clip.open(audioStream);
                                                                            clip.start();
                                                                            Thread.sleep(1000);
                                                                            addText("   " + getBot().getNomeBot() + ": " + perguntas[rand1.nextInt(((9 - 0) + 1) + 0)] + "\n");
                                                                            sendMessage("   " + getBot().getNomeBot() + ": " + perguntas[rand1.nextInt(((9 - 0) + 1) + 0)] + "\n");
                                                                            addText("\n");
                                                                            dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                            falando = false;
                                                                        }
                                                                    } catch (Exception e) {
                                                                        JOptionPane.showMessageDialog(rootPane, e);
                                                                    }
                                                                }
                                                            },
                                                            rand1.nextInt((7500 - 4800) + 1) + 4800
                                                    );

                                                }
                                                respondido = false;
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    },
                                    60000
                            );
                            respondido = true;
                        }

                        if (!falando) {
                            respBot = 0;
                            int resposta = 0;
                            int i = 0;
                            while (resposta == 0 && botAtivado) {
                                for (i = 0; i < chatBot.length - 1; i += 2) {
                                    if (chatBot[i].length == 3) {
                                        if (msgLow.contains(chatBot[i][0]) && msgLow.contains(chatBot[i][1]) && msgLow.contains(chatBot[i][2])) {
                                            respBot = 1;
                                            ilol = i;
                                        }
                                    }
                                    if (chatBot[i].length == 2) {
                                        if (msgLow.contains(chatBot[i][0]) && msgLow.contains(chatBot[i][1])) {
                                            respBot = 1;
                                            ilol = i;
                                        }
                                    }
                                    if (chatBot[i].length == 1) {
                                        if (msgLow.contains(chatBot[i][0])) {
                                            respBot = 1;
                                            ilol = i;
                                        }
                                    }
                                }
                                if (i >= chatBot.length - 1) {
                                    if (respBot == 1) {
                                        digitando();
                                        digitou();
                                        resposta = 1;
                                    } else {
                                        resposta = 2;
                                    }
                                }
                            }
                            if (resposta == 2) {
                                if (msg.equals(this.getResponsavel().getSenhaResponsavel())) {
                                    FileInputStream fstream = new FileInputStream(getLogs().getEnderecoPaciente());
                                    DataInputStream in = new DataInputStream(fstream);
                                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                                    String strLine;
                                    String texto = "";
                                    while ((strLine = br.readLine()) != null) {
                                        if (strLine.contains("\n")) {
                                            texto += strLine;
                                        } else {
                                            texto += strLine + "\n";
                                        }
                                    }
                                    in.close();

                                    File file = new File(getLogs().getEnderecoPaciente());
                                    file.createNewFile();
                                    BufferedWriter output = new BufferedWriter(new FileWriter(file));

                                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                    Date date = new Date();

                                    output.write(texto + dateFormat.format(date) + "\n" + this.getResponsavel().getNomeResponsavel() + " entrou na Configuração." + "\n\n");

                                    output.close();

                                    Admin admin = new Admin();
                                    admin.setVisible(true);
                                    dispose();
                                } else {
                                    this.digitando();
                                    new java.util.Timer().schedule(
                                            new java.util.TimerTask() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        jLabelDigitando.setText("");
                                                        InputStream in = Main.class.getResourceAsStream("/Sons/Msg.wav");
                                                        InputStream bufferedIn = new BufferedInputStream(in);
                                                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                                                        clip = AudioSystem.getClip();
                                                        clip.open(audioStream);
                                                        clip.start();
                                                        Thread.sleep(1000);

                                                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/autismo", "root", "");
                                                        java.sql.Statement stmtp = connection.createStatement();
                                                        String sqlp = "SELECT id FROM estatistica";
                                                        ResultSet rsp = stmtp.executeQuery(sqlp);

                                                        Estatistica estatistica = new Estatistica();

                                                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                                        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
                                                        String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
                                                        String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                                                        String dateLog = day + "/" + month + "/" + year;

                                                        Date date = formatter.parse(dateLog);

                                                        rsp.last();
                                                        while (!encontradoEstatistica && !rsp.isBeforeFirst()) {
                                                            Long idr = rsp.getLong("id");
                                                            estatistica.encontradoId(idr);

                                                            if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                                                encontradoEstatistica = true;
                                                            }

                                                            if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                                                encontradoEstatistica = true;
                                                            }
                                                            rsp.previous();
                                                        }

                                                        encontradoEstatistica = false;

                                                        ++respostasNum;
                                                        if (respostasNum != 3) {
                                                            addText("   " + getBot().getNomeBot() + ": " + fugas[rand1.nextInt(((4 - 0) + 1) + 0)] + "\n");
                                                            sendMessage("   " + getBot().getNomeBot() + ": " + fugas[rand1.nextInt(((4 - 0) + 1) + 0)] + "\n");
                                                            addText("\n");
                                                            dialog.select(dialog.getText().length(), dialog.getText().length());
                                                            falando = false;
                                                        } else {
                                                            Calendar a = Calendar.getInstance(Locale.FRENCH);
                                                            a.setTime(getPaciente().getIdadePaciente());
                                                            Calendar b = Calendar.getInstance(Locale.FRENCH);
                                                            b.setTime(Calendar.getInstance().getTime());

                                                            int diff = b.get(YEAR) - a.get(YEAR);
                                                            if (a.get(MONTH) > b.get(MONTH) || (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
                                                                diff--;
                                                            }

                                                            if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                                                if (diff >= 10 && diff <= 14 && estatistica.getNota1() == null) {
                                                                    addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                    sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                    addText("\n");
                                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                    falando = false;
                                                                    checkMicrophoneSapo();
                                                                } else if (diff >= 15 && diff <= 20 && estatistica.getNota2() == null) {
                                                                    addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos praticar uma atividade?\n");
                                                                    sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos praticar uma atividade?\n");
                                                                    addText("\n");
                                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                    falando = false;
                                                                    checkMicrophoneAud();
                                                                } else if (diff >= 6 && diff <= 9 && estatistica.getNota3() == null) {
                                                                    addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                    sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                    addText("\n");
                                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                    falando = false;
                                                                    checkAma();
                                                                } else {
                                                                    addText("   " + getBot().getNomeBot() + ": " + fugas[rand1.nextInt(((4 - 0) + 1) + 0)] + "\n");
                                                                    sendMessage("   " + getBot().getNomeBot() + ": " + fugas[rand1.nextInt(((4 - 0) + 1) + 0)] + "\n");
                                                                    addText("\n");
                                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                    falando = false;
                                                                }
                                                            } else if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                                                if (diff >= 10 && diff <= 14) {
                                                                    addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                    sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                    addText("\n");
                                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                    falando = false;
                                                                    checkMicrophoneSapo();
                                                                } else if (diff >= 15 && diff <= 20) {
                                                                    addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos praticar uma atividade?\n");
                                                                    sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos praticar uma atividade?\n");
                                                                    addText("\n");
                                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                    falando = false;
                                                                    checkMicrophoneAud();
                                                                } else if (diff >= 6 && diff <= 9) {
                                                                    addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                    sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                                    addText("\n");
                                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                    falando = false;
                                                                    checkAma();
                                                                } else {
                                                                    addText("   " + getBot().getNomeBot() + ": " + fugas[rand1.nextInt(((4 - 0) + 1) + 0)] + "\n");
                                                                    sendMessage("   " + getBot().getNomeBot() + ": " + fugas[rand1.nextInt(((4 - 0) + 1) + 0)] + "\n");
                                                                    addText("\n");
                                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                                    falando = false;
                                                                }
                                                            }
                                                        }

                                                    } catch (Exception e) {
                                                        JOptionPane.showMessageDialog(rootPane, e);
                                                    }
                                                }
                                            },
                                            rand1.nextInt((7500 - 4800) + 1) + 4800
                                    );
                                }
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Digite alguma coisa antes de enviar", "Mensagem", 1);
                    }

                } else if (input.getText().equals(this.getResponsavel().getSenhaResponsavel())) {
                    Admin admin = new Admin();
                    admin.setVisible(true);
                    dispose();

                } else {
                    input.setEditable(false);
                    JOptionPane.showMessageDialog(null, getBot().getNomeBot() + " não consegue falar com você.", "Comunicação", 1);
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void digitando() {
        falando = true;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        jLabelDigitando.setText(getBot().getNomeBot() + " está digitando...");
                    }
                },
                rand.nextInt((3800 - 3500) + 1) + 3500
        );
    }

    public void digitou() {
        Thread digitar = new Thread(new Runnable() {
            public void run() {
                try {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    try {
                                        jLabelDigitando.setText("");
                                        InputStream in = Main.class.getResourceAsStream("/Sons/Msg.wav");
                                        InputStream bufferedIn = new BufferedInputStream(in);
                                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                                        clip = AudioSystem.getClip();
                                        clip.open(audioStream);
                                        clip.start();

                                        Thread.sleep(
                                                1000);

                                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/autismo", "root", "");
                                        java.sql.Statement stmtp = connection.createStatement();
                                        String sqlp = "SELECT id FROM estatistica";
                                        ResultSet rsp = stmtp.executeQuery(sqlp);

                                        Estatistica estatistica = new Estatistica();

                                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
                                        String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
                                        String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                                        String dateLog = day + "/" + month + "/" + year;

                                        Date date = formatter.parse(dateLog);

                                        rsp.last();
                                        while (!encontradoEstatistica && !rsp.isBeforeFirst()) {
                                            Long idr = rsp.getLong("id");
                                            estatistica.encontradoId(idr);

                                            if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                                encontradoEstatistica = true;
                                            }

                                            if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                                encontradoEstatistica = true;
                                            }
                                            rsp.previous();
                                        }

                                        encontradoEstatistica = false;

                                        ++respostasNum;
                                        if (respostasNum != 3) {
                                            addText("   " + getBot().getNomeBot() + ": " + chatBot[ilol + 1][0] + "\n");
                                            sendMessage("   " + getBot().getNomeBot() + ": " + chatBot[ilol + 1][0] + "\n");
                                            addText("\n");
                                            dialog.select(dialog.getText().length(), dialog.getText().length());
                                            falando = false;
                                        } else {
                                            Calendar a = Calendar.getInstance(Locale.FRENCH);
                                            a.setTime(getPaciente().getIdadePaciente());
                                            Calendar b = Calendar.getInstance(Locale.FRENCH);
                                            b.setTime(Calendar.getInstance().getTime());

                                            int diff = b.get(YEAR) - a.get(YEAR);
                                            if (a.get(MONTH) > b.get(MONTH) || (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
                                                diff--;
                                            }

                                            if (estatistica.getData().equals(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                                if (diff >= 10 && diff <= 14 && estatistica.getNota1() == null) {
                                                    addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                    sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                    addText("\n");
                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                    falando = false;
                                                    checkMicrophoneSapo();
                                                } else if (diff >= 15 && diff <= 20 && estatistica.getNota2() == null) {
                                                    addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos praticar uma atividade?\n");
                                                    sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos praticar uma atividade?\n");
                                                    addText("\n");
                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                    falando = false;
                                                    checkMicrophoneAud();
                                                } else if (diff >= 6 && diff <= 9 && estatistica.getNota3() == null) {
                                                    addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                    sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                    addText("\n");
                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                    falando = false;
                                                    checkAma();
                                                } else {
                                                    addText("   " + getBot().getNomeBot() + ": " + chatBot[ilol + 1][0] + "\n");
                                                    sendMessage("   " + getBot().getNomeBot() + ": " + chatBot[ilol + 1][0] + "\n");
                                                    addText("\n");
                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                    falando = false;
                                                }
                                            } else if (estatistica.getData().before(date) && estatistica.getNomePaciente().equals(getPaciente().getNomePaciente())) {
                                                if (diff >= 10 && diff <= 14) {
                                                    addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                    sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                    addText("\n");
                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                    falando = false;
                                                    checkMicrophoneSapo();
                                                } else if (diff >= 15 && diff <= 20) {
                                                    addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos praticar uma atividade?\n");
                                                    sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos praticar uma atividade?\n");
                                                    addText("\n");
                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                    falando = false;
                                                    checkMicrophoneAud();
                                                } else if (diff >= 6 && diff <= 9) {
                                                    addText("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                    sendMessage("   " + getBot().getNomeBot() + ": " + "Parece que já conversamos bastante, vamos brincar?\n");
                                                    addText("\n");
                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                    falando = false;
                                                    checkAma();
                                                } else {
                                                    addText("   " + getBot().getNomeBot() + ": " + chatBot[ilol + 1][0] + "\n");
                                                    sendMessage("   " + getBot().getNomeBot() + ": " + chatBot[ilol + 1][0] + "\n");
                                                    addText("\n");
                                                    dialog.select(dialog.getText().length(), dialog.getText().length());
                                                    falando = false;
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(rootPane, e);
                                    }
                                }
                            },
                            rand1.nextInt((7500 - 4800) + 1) + 4800
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        digitar.start();
    }

    public void keyReleased(KeyEvent e) {
        if (!input.getText().equals("")) {
            this.jButtonEnviar.setEnabled(true);
        } else {
            this.jButtonEnviar.setEnabled(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            input.setText("");
            input.setEditable(true);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void addText(String str) {
        dialog.setText(dialog.getText() + str);
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public Bot getBot() {
        return this.bot;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Responsavel getResponsavel() {
        return this.responsavel;
    }

    public void setLogs(Logs logs) {
        this.logs = logs;
    }

    public Logs getLogs() {
        return this.logs;
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane dialog;
    private javax.swing.JEditorPane input;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonBot;
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JButton jButtonMudar;
    private javax.swing.JButton jButtonPaciente;
    private javax.swing.JLabel jLabelDigitando;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextFieldBot;
    private javax.swing.JTextField jTextFieldPaciente;
    // End of variables declaration//GEN-END:variables
}
