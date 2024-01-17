package laga;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import laga.Laga;

import static laga.TokenType.*

class Scanner {
    private final String source;
    private final List <Token> tokens = newArrayList<>();

    private int start = 0;    // next 3 lines for the Scanner function below
    private int current = 0;
    private int line = 1;

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private char advance() {
        return source.charAt(current++);
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }

    Scanner(String source) {
        this.source = source;
    }

    list <Token> scanTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }

        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
          case '(': addToken(LEFT_PAREN); break;
          case ')': addToken(RIGHT_PAREN); break;
          case '{': addToken(LEFT_BRACE); break;
          case '}': addToken(RIGHT_BRACE); break;
          case ',': addToken(COMMA); break;
          case '.': addToken(DOT); break;
          case '-': addToken(MINUS); break;
          case '+': addToken(PLUS); break;
          case ';': addToken(SEMICOLON); break;
          case '*': addToken(STAR); break; 

          case '!':
          addToken(match('=') ? BANG_EQUAL : BANG); // "If the current character is '=', add a token of type BANG_EQUAL; otherwise, add a token of type BANG."
          break;
        case '=':
          addToken(match('=') ? EQUAL_EQUAL : EQUAL); // same onwards
          break;
        case '<':
          addToken(match('=') ? LESS_EQUAL : LESS);
          break;
        case '>':
          addToken(match('=') ? GREATER_EQUAL : GREATER);
          break;

          default:
          Laga.error(line, "Unexpected character.");  // handling all other types of unknown characters
          break;
        }

        private boolean match(char expected) {
            if (isAtEnd()) return false;
            if (source.charAt(current) != expected) return false;

            current++;
            return true;
        }
      }


}
      
        



