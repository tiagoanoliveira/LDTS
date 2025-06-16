# GIT

## 1. O que é Git e por que usá-lo?

Git é um **sistema de controlo de versões** que permite acompanhar e gerenciar alterações em arquivos ao longo do tempo. Imagina que estás a trabalhar num projeto e queres guardar diferentes versões dele à medida que avanças - o Git faz exatamente isso, mas de forma muito mais eficiente do que criar cópias manuais dos arquivos.

### Principais benefícios do Git:

- **Histórico completo**: Mantém um registro detalhado de todas as alterações feitas no projeto
- **Trabalho colaborativo**: Permite que várias pessoas trabalhem no mesmo projeto simultaneamente
- **Segurança**: Dificulta a perda de trabalho, pois todas as versões anteriores ficam guardadas
- **Experimentação segura**: Possibilita testar novas ideias sem medo de arruinar o projeto principal
- **Rastreabilidade**: Ajuda a entender quando, por que e por quem as alterações foram feitas

Git foi criado por Linus Torvalds (o mesmo criador do Linux) em 2005 para ajudar no desenvolvimento do kernel Linux. Hoje, é a ferramenta de controle de versão mais utilizada no mundo do desenvolvimento de software.

## 2. Conceitos fundamentais

Antes de começar a usar Git, é importante entender alguns conceitos básicos:

### Repositório

Um **repositório** (ou "repo") é onde o Git armazena todas as informações sobre o teu projeto. Basicamente, é uma pasta do teu projeto com um diretório especial oculto chamado `.git` que contém todo o histórico e configurações.

Existem dois tipos de repositórios:
- **Repositório local**: Está no teu computador
- **Repositório remoto**: Está num servidor (como GitHub, GitLab, Bitbucket)

### Commits

Um **commit** é como uma "fotografia" do teu projeto num determinado momento. Cada commit recebe um identificador único (hash) e contém:
- As alterações feitas desde o último commit
- Uma mensagem descrevendo o que foi alterado
- Informações sobre quem fez as alterações e quando

Commits são a unidade básica do histórico do Git. Eles permitem reverter para estados anteriores do projeto se necessário.

### Branches (Ramos)

Um **branch** é uma linha independente de desenvolvimento. Podes pensar nele como um caminho alternativo que o teu projeto pode seguir.

O branch principal geralmente é chamado de `main` ou `master`. Quando queres adicionar uma nova funcionalidade ou corrigir um problema sem afetar o código principal, crias um novo branch. Depois, quando estiveres satisfeito com as alterações, podes juntar (fazer "merge") esse branch de volta ao branch principal.

### As três áreas do Git

Git divide o teu trabalho em três áreas distintas:

1. **Working Directory (Diretório de Trabalho)**:
   - São os arquivos que estás a editar no teu computador
   - Onde realmente fazes as alterações no código

2. **Staging Area (Área de Preparação)**:
   - Uma área intermediária onde selecionas quais alterações queres incluir no próximo commit
   - Permite escolher exatamente o que será "fotografado" pelo Git

3. **Repository (Repositório Git)**:
   - Onde o Git armazena o histórico de commits
   - Contém todos os dados de todas as versões do projeto

![Git Areas](../Images/gitStages.png)

A imagem acima mostra o fluxo básico entre estas três áreas:
- Usas `git add` para mover alterações do Diretório de Trabalho para a Área de Preparação
- Usas `git commit` para mover alterações da Área de Preparação para o Repositório
- Usas `git checkout` para trazer alterações do Repositório de volta para o Diretório de Trabalho

### Estados dos arquivos no Git

No Git, um arquivo pode estar em quatro estados diferentes:

1. **Untracked (Não rastreado)**:
   - Git não está a monitorizar este arquivo
   - Arquivos novos começam neste estado
   - Git ignora completamente qualquer alteração neste arquivo

2. **Unmodified (Não modificado)**:
   - O arquivo está a ser rastreado pelo Git e não foi alterado desde o último commit
   - Está salvo no repositório exatamente como está no teu diretório de trabalho

3. **Modified (Modificado)**:
   - O arquivo foi alterado desde o último commit
   - As alterações ainda não foram preparadas para o próximo commit

4. **Staged (Preparado)**:
   - O arquivo foi modificado e marcado para ser incluído no próximo commit
   - Está na área de preparação esperando ser confirmado

![File States](../Images/fileStages.png)

A imagem acima ilustra como os arquivos transitam entre estes estados:
- Usar `git add` num arquivo não rastreado começa a rastreá-lo e coloca-o no estado "staged"
- Editar um arquivo não modificado torna-o modificado
- Usar `git add` num arquivo modificado coloca-o no estado "staged"
- Usar `git commit` transforma todos os arquivos staged em não modificados
- Usar `git rm` num arquivo rastreado faz com que ele volte ao estado não rastreado

## 3. Primeiros passos com Git

### Instalação

Antes de mais nada, precisas de instalar o Git no teu computador:

**Windows**:
- Descarrega o instalador em [git-scm.com](https://git-scm.com/download/win)
- Segue as instruções do instalador (as opções padrão geralmente funcionam bem)

**macOS**:
- Instala através do Terminal: `brew install git` (se tiveres Homebrew)
- Ou descarrega em [git-scm.com](https://git-scm.com/download/mac)

**Linux (Ubuntu/Debian)**:
- Instala através do Terminal: `sudo apt-get install git`

### Configuração inicial

Após a instalação, é importante configurar a tua identidade no Git. Isto ajuda a identificar quem fez cada alteração no projeto:

```bash
# Configurar nome de utilizador global
git config --global user.name "Teu Nome"

# Configurar email global
git config --global user.email "teu.email@exemplo.com"
```

Esta configuração é feita apenas uma vez no teu computador e será usada em todos os teus repositórios Git.

### Criando o teu primeiro repositório

Para começar a usar Git num projeto:

1. **Criar um novo repositório**:
   ```bash
   # Navega até a pasta do teu projeto
   cd caminho/para/teu/projeto
   
   # Inicia um repositório Git
   git init
   ```

   Isto cria um diretório oculto `.git` que contém toda a estrutura necessária. O teu projeto agora é um repositório Git!

2. **Ou clonar um repositório existente**:
   Se quiseres trabalhar num projeto que já existe online (por exemplo, no GitHub):

   ```bash
   # Cria uma cópia local de um repositório remoto
   git clone https://github.com/utilizador/nome-do-projeto.git
   ```

   Isto descarrega todo o projeto e seu histórico para o teu computador.

## Fluxo de trabalho básico com Git

O fluxo de trabalho típico com Git segue estes passos:

### 1. Verificar o estado atual

Antes de fazer qualquer coisa, é sempre bom verificar em que estado está o teu repositório:

```bash
git status
```

Este comando mostra:
- Em qual branch estás
- Quais arquivos foram modificados mas ainda não foram "staged"
- Quais arquivos estão "staged" e prontos para commit
- Quais arquivos não estão sendo rastreados

É como uma "bússola" que te orienta sobre o estado atual do teu trabalho e o que podes fazer a seguir.

### 2. Preparar alterações para commit

Depois de fazer alterações nos arquivos, precisas decidir quais delas queres incluir no próximo commit:

```bash
# Adicionar um arquivo específico à área de preparação
git add nome_do_arquivo.txt

# Adicionar vários arquivos
git add arquivo1.txt arquivo2.txt

# Adicionar todos os arquivos modificados
git add .
```

**Por que usar a área de preparação?** Ela permite controlar exatamente o que entra em cada commit. Imagina que estás a trabalhar em várias alterações, mas queres commitá-las separadamente para manter o histórico organizado - a área de preparação torna isso possível.

### 3. Confirmar as alterações (commit)

Quando estiveres satisfeito com o que está na área de preparação, é hora de criar um commit:

```bash
git commit -m "Descrição clara do que foi alterado"
```

A mensagem de commit é muito importante! Uma boa mensagem explica **o que** foi alterado e **por que** foi alterado. Isto será extremamente útil no futuro quando quiseres entender por que certas alterações foram feitas.

**Dica prática**: Pensa no commit como uma entrada num diário do projeto. A mensagem deve ser clara o suficiente para que tu (ou outra pessoa) entenda o propósito da alteração mesmo meses depois.

### 4. Ver o histórico de alterações

Para ver o histórico de commits do projeto:

```bash
# Ver histórico completo
git log

# Ver histórico resumido (uma linha por commit)
git log --oneline
```

Este comando mostra:
- O identificador único (hash) de cada commit
- O autor e data
- A mensagem de commit

É como uma "máquina do tempo" que te permite ver a evolução do projeto e entender quando e por que cada alteração foi feita.

## 4. Trabalhando com branches

Os branches ("ramos") são uma das funcionalidades mais poderosas do Git. Eles permitem trabalhar em diferentes versões do teu projeto em paralelo.

### Por que usar branches?

Imagine que estás a desenvolver um site que já está online. Queres adicionar uma nova funcionalidade, mas não queres arriscar quebrar o site existente enquanto trabalhas nela. Branches resolvem exatamente este problema!

### Criando e usando branches

**Criar um novo branch**:
```bash
# Criar um novo branch
git branch nova-funcionalidade

# Criar e mudar para o novo branch em um só comando
git checkout -b nova-funcionalidade
```

**Mudar entre branches**:
```bash
git checkout nome-do-branch
```

Quando mudas de branch, os arquivos no teu diretório de trabalho mudam automaticamente para refletir o estado daquele branch. É como entrar numa "realidade alternativa" do teu projeto!

**Ver todos os branches**:
```bash
git branch
```

O branch atual será marcado com um asterisco (*).

### Unir branches (merge)

Quando terminares o trabalho num branch e quiseres incorporá-lo ao branch principal:

```bash
# Primeiro, volta para o branch de destino (geralmente main ou master)
git checkout main

# Depois, une o outro branch a este
git merge nova-funcionalidade
```

**O que acontece durante um merge?**
- Git tenta combinar automaticamente as alterações dos dois branches
- Se as alterações estiverem em partes diferentes dos arquivos, Git faz isso automaticamente
- Se houver alterações conflitantes na mesma parte de um arquivo, será necessário resolver manualmente

### Resolvendo conflitos de merge

Conflitos ocorrem quando as mesmas linhas de um arquivo foram alteradas de maneiras diferentes em branches diferentes. Quando isto acontece:

1. Git marca no arquivo as áreas conflitantes:
   ```
   <<<<<<< HEAD
   Conteúdo do branch atual
   =======
   Conteúdo do branch que está sendo unido
   >>>>>>> nova-funcionalidade
   ```

2. Edita o arquivo para resolver o conflito:
   - Remove os marcadores (`<<<<<<<`, `=======`, `>>>>>>>`)
   - Ajusta o conteúdo para que fique como desejas

3. Depois de resolver todos os conflitos:
   ```bash
   git add arquivo-com-conflito
   git commit
   ```

Git abrirá um editor com uma mensagem de commit padrão para o merge. Podes manter essa mensagem ou personalizá-la.

## 5. Trabalhando com repositórios remotos

Para colaborar com outros ou guardar o teu código na nuvem, precisas trabalhar com repositórios remotos (como GitHub, GitLab, Bitbucket).

### Conectando a um repositório remoto

Se iniciaste o teu repositório localmente:

```bash
# Adicionar um repositório remoto
git remote add origin https://github.com/teu-usuario/teu-repo.git

# Ver todos os repositórios remotos configurados
git remote -v
```

"Origin" é apenas um nome convencionado para o repositório remoto principal, mas podes escolher outro nome se preferires.

### Enviando alterações para o repositório remoto

Depois de fazer commits localmente, podes enviá-los para o remoto:

```bash
# Enviar um branch para o repositório remoto
git push origin nome-do-branch

# Se for a primeira vez que envias este branch, configura o rastreamento
git push -u origin nome-do-branch
```

O parâmetro `-u` (ou `--set-upstream`) estabelece uma relação entre o teu branch local e o branch remoto. Depois de configurar isso, podes simplesmente usar `git push` nas próximas vezes.

### Obtendo alterações do repositório remoto

Para atualizar o teu repositório local com as alterações feitas por outras pessoas:

```bash
# Baixar alterações do repositório remoto
git fetch origin

# Baixar e aplicar alterações ao teu branch atual
git pull origin nome-do-branch
```

A diferença entre `fetch` e `pull`:
- `fetch` apenas baixa as alterações, mas não as aplica ao teu trabalho
- `pull` é basicamente um `fetch` seguido de um `merge` - baixa e aplica as alterações

## 6. Gerenciando arquivos

### Ignorando arquivos

Nem todos os arquivos precisam ou devem ser controlados pelo Git (como arquivos de configuração pessoal, dependências instaladas, etc.). Para isso, usa-se o arquivo `.gitignore`:

1. Cria um arquivo chamado `.gitignore` na raiz do teu projeto
2. Lista os padrões de arquivos que devem ser ignorados:

```
# Ignora arquivos de configuração pessoal
config.local.ini

# Ignora todos os arquivos de log
*.log

# Ignora a pasta de dependências
node_modules/
```

**Dica prática**: Adiciona o `.gitignore` logo no início do projeto para evitar commitar acidentalmente arquivos que deveriam ser ignorados.

### Removendo arquivos

Para remover arquivos do projeto:

```bash
# Remove o arquivo do repositório E do sistema de arquivos
git rm arquivo.txt

# Remove o arquivo apenas do repositório (mantém no sistema de arquivos)
git rm --cached arquivo.txt
```

Após usar `git rm`, ainda precisas fazer um commit para confirmar a remoção.

## 7. Desfazendo alterações

Uma das grandes vantagens do Git é que quase tudo pode ser desfeito. Aqui estão várias situações e como resolvê-las:

### Alterações não commitadas

**Descartar alterações em um arquivo**:
```bash
git checkout -- nome-do-arquivo
```

**Descartar todas as alterações**:
```bash
git checkout -- .
```

Estes comandos descartam permanentemente as alterações feitas, restaurando os arquivos para o estado do último commit.

### Alterações staged mas não commitadas

**Remover um arquivo da área de preparação (staging)**:
```bash
git reset HEAD nome-do-arquivo
```

Isto mantém as alterações no arquivo, mas remove-o da área de preparação.

### Desfazer o último commit

**Desfazer o commit mas manter as alterações**:
```bash
git reset --soft HEAD~1
```

Isto move o branch para o commit anterior, mas mantém todas as alterações do commit desfeito na área de preparação, prontas para serem recommitadas.

**Desfazer completamente o último commit**:
```bash
git reset --hard HEAD~1
```

Cuidado! Isto descarta permanentemente todas as alterações do último commit.

### Corrigir o último commit

Se esqueceste de adicionar um arquivo ou queres corrigir a mensagem do último commit:

```bash
# Adicionar arquivos esquecidos
git add arquivo-esquecido

# Corrigir o último commit
git commit --amend -m "Nova mensagem de commit"
```

Este comando substitui o último commit por um novo. Só use isto se ainda não tiveres enviado o commit para um repositório remoto.

## 8. Exemplos práticos

### Exemplo 1: Iniciando um projeto pessoal

Vamos ver um exemplo prático de como começar um projeto do zero:

```bash
# 1. Criar uma pasta para o projeto
mkdir meu-site

# 2. Entrar na pasta
cd meu-site

# 3. Inicializar o repositório Git
git init

# 4. Criar alguns arquivos iniciais
echo "# Meu Site Pessoal" > 'README1.md'
echo "<!DOCTYPE html><html><body><h1>Olá, mundo!</h1></body></html>" > index.html

# 5. Verificar o estado
git status
# Verás que README1.md e index.html estão como "untracked"

# 6. Adicionar os arquivos à área de preparação
git add README1.md index.html

# 7. Fazer o primeiro commit
git commit -m "Commit inicial: README e página HTML básica"

# 8. Verificar o histórico
git log --oneline
```

### Exemplo 2: Trabalhando com branches

Vamos ver como trabalhar com branches num cenário real:

```bash
# 1. Criar e mudar para um novo branch para uma nova funcionalidade
git checkout -b adicionar-estilo

# 2. Criar um arquivo CSS
echo "body { font-family: Arial; color: #333; }" > estilo.css

# 3. Modificar o HTML para incluir o CSS
echo '<!DOCTYPE html><html><head><link rel="stylesheet" href="estilo.css"></head><body><h1>Olá, mundo!</h1></body></html>' > index.html

# 4. Adicionar e commitar as alterações
git add estilo.css index.html
git commit -m "Adiciona estilo básico à página"

# 5. Voltar para o branch principal
git checkout main

# 6. Unir o branch de funcionalidade ao principal
git merge adicionar-estilo

# 7. Verificar o histórico após o merge
git log --graph --oneline
```

### Exemplo 3: Colaborando com um repositório remoto

Supondo que tens um projeto no GitHub e queres contribuir:

```bash
# 1. Clonar o repositório
git clone https://github.com/utilizador/projeto.git
cd projeto

# 2. Criar um branch para tua contribuição
git checkout -b corrigir-bug

# 3. Fazer alterações, adicionar e commitar
# (editar arquivos...)
git add arquivo-alterado
git commit -m "Corrige bug na funcionalidade X"

# 4. Enviar o branch para o GitHub
git push -u origin corrigir-bug

# 5. Agora podes criar um "Pull Request" no GitHub
```

## 9. Boas práticas para iniciantes

### Commits eficazes

- **Commit com frequência**: Commits pequenos e frequentes são mais fáceis de entender e reverter
- **Mensagens claras**: Uma boa mensagem explica "o quê" e "por quê", não o "como"
- **Um propósito por commit**: Cada commit deve representar uma única alteração lógica

### Organização

- **Crie um README.md**: Documenta o que é o projeto e como usá-lo
- **Use branches**: Isole o desenvolvimento de novas funcionalidades em branches separados
- **Commits organizados**: Mantenha commits relacionados juntos e separados de outros assuntos

### Colaboração

- **Pull antes de push**: Sempre baixe as alterações mais recentes antes de enviar as suas
- **Respeite o trabalho alheio**: Ao modificar código escrito por outros, entenda a intenção original
- **Comunique**: Use mensagens de commit e pull requests para explicar claramente suas intenções

## 10. Ferramentas visuais para Git

Se preferires uma interface gráfica em vez de linha de comando:

- **GitHub Desktop**: Fácil de usar, integração perfeita com GitHub
- **GitKraken**: Interface poderosa e intuitiva
- **Sourcetree**: Rico em recursos, bom para usuários avançados
- **VS Code**: Editor de código com excelente integração Git integrada

## 11. Resolvendo problemas comuns

### "Help! Commitei no branch errado!"

1. Anota o hash do commit (use `git log`)
2. Muda para o branch correto: `git checkout branch-correto`
3. Aplica o commit: `git cherry-pick hash-do-commit`
4. Volta ao branch original: `git checkout branch-original`
5. Remove o commit: `git reset --hard HEAD~1`

### "Quero descartar todas as minhas alterações locais!"

```bash
git fetch origin
git reset --hard origin/main
```

Isto substitui completamente o teu branch local pelo estado do branch remoto.

### "Fiz push de algo que não devia!"

Se enviaste informações sensíveis (como senhas):

1. Remove a informação sensível
2. Commita a correção
3. Entre em contato com o administrador do repositório, pois o histórico precisa ser reescrito

## 12. Glossário de termos Git

- **Branch**: Uma linha independente de desenvolvimento
- **Clone**: Cópia local de um repositório remoto
- **Commit**: Uma "fotografia" das alterações feitas num determinado momento
- **Fetch**: Baixar alterações do repositório remoto sem aplicá-las
- **Fork**: Cópia pessoal de um repositório de outra pessoa
- **HEAD**: Referência ao commit atual em que estás a trabalhar
- **Master/Main**: O branch principal do repositório
- **Merge**: Combinar alterações de um branch em outro
- **Origin**: Nome padrão para o repositório remoto de onde clonaste
- **Pull**: Baixar alterações do repositório remoto e aplicá-las
- **Push**: Enviar commits locais para o repositório remoto
- **Repository**: Coleção de arquivos e histórico do projeto
- **Stage**: Preparar alterações para serem commitadas

## 13. Recursos para aprender mais

### Tutoriais interativos gratuitos:
- [Git-it](https://github.com/jlord/git-it-electron): Tutorial desktop interativo
- [Learn Git Branching](https://learngitbranching.js.org/): Visualizador interativo de branches
- [GitHub Learning Lab](https://lab.github.com/): Cursos práticos hospedados no GitHub

### Documentação oficial:
- [Git Book](https://git-scm.com/book/pt-br/v2): Livro completo gratuito sobre Git
- [Git Reference](https://git-scm.com/docs): Documentação oficial de comandos

### Comunidades para ajuda:
- [Stack Overflow](https://stackoverflow.com/questions/tagged/git): Perguntas e respostas
- [GitHub Community Forum](https://github.community/): Fórum da comunidade GitHub

## 14. Conclusão

Git é uma ferramenta poderosa que pode parecer intimidante no início, mas com a prática, torna-se uma aliada indispensável no desenvolvimento de software. Lembra-te:

- Não tenhas medo de experimentar - Git foi feito para permitir experimentação segura
- A prática leva à perfeição - quanto mais usares, mais confortável te sentirás
- Todos cometem erros com Git - há quase sempre uma maneira de recuperar

O mais importante é começar a usar Git em projetos reais. Mesmo que comeces apenas com os comandos básicos (`init`, `add`, `commit`, `status`, `log`), já estarás a melhorar significativamente o teu fluxo de trabalho


#### @Tiago Oliveira, 2025. Feito com o apoio de Claude.AI