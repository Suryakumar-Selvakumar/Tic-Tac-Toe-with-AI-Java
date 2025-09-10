# Tic-Tac-Toe with AI

This project is an implementation of the classic Tic-Tac-Toe game, extended with AI opponents at different difficulty
levels. The program allows for matches between two humans, a human and the computer, or even two computer-controlled
players. Development progressed in stages, each adding new functionality:

1. **Stage 1 - Initial setup:**  
   Implemented the 3×3 grid with coordinate-based moves. Added validation for invalid input, occupied cells, and
   out-of-range coordinates. The program could also evaluate the game state, reporting outcomes such as *X wins*, *O
   wins*, *Draw*, or *Game not finished*.

2. **Stage 2 - Easy does it:**  
   Introduced the first AI opponent at the *easy* difficulty level. This AI chooses random valid moves, providing a
   simple challenge for beginners. The game loop alternates turns between the user (X) and the computer (O).

3. **Stage 3 - Watch 'em fight:**  
   Added a menu system with the `start` and `exit` commands. Players can now configure who controls X and O, choosing
   between *user* and *easy* AI. This also enabled computer vs. computer games and two-human matches, along with
   handling invalid menu parameters.

4. **Stage 4 - Signs of intelligence:**  
   Introduced the *medium* difficulty AI. This AI looks ahead one move, taking a winning move if available or blocking
   the opponent’s winning move. If neither case applies, it falls back to a random move. This made the computer much
   harder to beat compared to the *easy* level.

5. **Stage 5 - An undefeated champion:**  
   Implemented the *hard* difficulty AI using the minimax algorithm. Unlike the *medium* AI, which only reacts to
   immediate threats, the *hard* AI simulates all possible moves to the end of the game tree and always chooses the
   optimal one. This ensures flawless play regardless of the opponent’s skill level, making the AI unbeatable.

## Demo

<video width="1920" height="1080" align="center" src="https://github.com/user-attachments/assets/8a8f1fba-ef7b-4a95-87a7-5d01c6cfd6d4"></video>


## Takeaway

This project deepened my experience with turn-based game logic, grid-based state analysis, and AI strategy in Java. I
strengthened my ability to validate input, manage alternating turns between multiple types of players, and design a menu
system that allows flexible configurations. Building the *easy* and *medium* AIs gave me practical insight into
rule-based decision-making, while implementing the *hard* AI with minimax bolstered my understanding of recursion,
exhaustive search, and optimal strategy design. By completing this project, I gained confidence in applying classical
algorithms to solve real problems and appreciated how increasing AI sophistication transforms the player experience.
