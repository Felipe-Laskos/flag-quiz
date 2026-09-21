# FlagQuiz

Trabalho Prático 1 – Desenvolvimento Mobile (Android / Kotlin).

Quiz de bandeiras: a cada rodada o app sorteia 5 bandeiras (entre 30 disponíveis, sem repetição)
e o jogador precisa digitar o nome do país. Cada acerto vale 20 pontos.

## Telas

| Activity         | Layout                 | Função                                                                 |
|------------------|------------------------|------------------------------------------------------------------------|
| `MainActivity`   | `activity_main.xml`    | Logotipo, campo para o nome do jogador e botão **Começar**             |
| `QuizActivity`   | `activity_quiz.xml`    | Contador "Pergunta N de 5", bandeira, campo de resposta, aviso Correto/Incorreto e botão **Próxima** |
| `ResultActivity` | `activity_result.xml`  | Nome do jogador, pontuação final e botão **Recomeçar**                 |

## Regras implementadas

- 5 perguntas por partida, sorteadas sem repetição com `Random.nextInt`.
- Resposta comparada sem diferenciar maiúsculas/minúsculas (e também sem diferenciar acentos).
- "Correto!" em verde ou "Incorreto!" em vermelho, mostrando o país certo quando o jogador erra.
- Pontuação: 20 pontos por acerto (máximo 100).
- Nome e pontuação são enviados entre as Activities via `Intent.putExtra`.

## Integrantes

Lucas Sarnacki Guiraud - GRR20241773
Felipe Matheus Laskos - GRR20241326
João Vitor Zanini Pedro - GRR20242373
