# Screen Sound

> Projeto que possibilita você a cadastrar e consultar sobre seus artistas e músicas favoritos!

### Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas para as seguintes tarefas:

- [x] Cadastro das músicas e artistas.
- [x] Consulta das músicas e artistas.
- [x] Consulta a API do gemini sobre seu artista favorito.
- [ ] Construção da página web.
- [ ] Consultas realizadas através da pagina web.

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- Você instalou os seguintes requisitos: `< Java 21 / Langchain4j v.0.35.0, Spring data>`
- Você tem uma máquina `<Windows (compatível.) / Linux (não compatível por enquanto.) / Mac (compatível)>`.

## 🚀 Instalando ScreenSound

Para instalar o Screen Sound, siga estas etapas:

Linux e macOS:

```
<comando_de_instalação>
```

Windows:

```
<comando_de_instalação>
```

## ☕ Usando ScreenSound

Para usar <nome_do_projeto>, siga estas etapas:

1. Primeiro passe pelo menu:
```
*** Screen Sound Musicas ***                

1 - Register artists.
2 - Register Musics.
3 - List musics.
4 - List artists
5 - Search musics by artist.
6 - Search musics by genre.
7 - Search information about artist.

0 - exit

```
2. Registre o artista, depois registre a música:
```
Type the artist name: 
<artist name> 

Type the artist birthday (01/01/9999): 
<artist birthday>

Type the genre that this artist sings: 
<artist genre sings>

Enter the artist type:
<artist type>
```
3. Registre a música desse artista (uma por uma): 
```
Type the music: 
<music name>

Type the duration music (in seconds): 
<music duration>

Type the artist sings that music: 
<artist> 

Type the genre music: 
<genre music>
```
4. Exemplo da busca dos artistas: 
````
Artist name: tim maia 
ArtistBirthday: 1942-08-28 
Artist musics: [
musicName = gostava tanto de você, 
musicDuration = 256.0 seconds, 
artist = tim maia, 
genreMusic = MPB
, 
musicName = casinha de sapê, 
musicDuration = 123.0 seconds, 
artist = tim maia, 
genreMusic = MPB
] 
Sung genre: MPB
````
5. Exemplo da lista de músicas:
````
Music name: gostava tanto de você 
Music duration (in seconds): 256.00
Artists: tim maia 
Music genre: MPB

Music name: casinha de sapê 
Music duration (in seconds): 123.00
Artists: tim maia 
Music genre: MPB
````

## 📫 Contribuindo para Screen Sound

Para contribuir com Screen Sound, siga estas etapas:

1. Bifurque este repositório.
2. Crie um branch: `git checkout -b <nome_branch>`.
3. Faça suas alterações e confirme-as: `git commit -m '<mensagem_commit>'`
4. Envie para o branch original: `git push origin <nome_do_projeto> / <local>`
5. Crie a solicitação de pull.

Como alternativa, consulte a documentação do GitHub em [como criar uma solicitação pull](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request).

## 👨🏻‍💻 Criador do projeto: 

Agradecemos às seguintes pessoas que contribuíram para este projeto:

<table>
  <tr>
    <td align="center">
      <a href="#" title="imagem do criador">
        <img src="https://avatars.githubusercontent.com/u/183449649?v=4" width="100px;" alt="Foto do Felipe"/><br>
        <sub>
          <b>Felipe Andrade</b>
        </sub>
      </a>
  </tr>
</table>
