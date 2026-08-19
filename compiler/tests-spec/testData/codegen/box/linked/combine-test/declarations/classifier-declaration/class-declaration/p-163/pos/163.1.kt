// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 163 -> sentence 163
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 163 -> sentence 163
 *                inheritance, inheriting -> paragraph 163 -> sentence 163
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 163 -> sentence 163
 * NUMBER: 1
 * DESCRIPTION: sealed class may be inherited by same-file top-level subclasses via constructor delegation, including data-class inheritors and exhaustive when dispatch in class declaration
 */

// TESTCASE NUMBER: 1
sealed class Expr

data class Num(val n: Int) : Expr()

data class Sum(val left: Expr, val right: Expr) : Expr()

fun eval(e: Expr): Int = when (e) {
    is Num -> e.n
    is Sum -> eval(e.left) + eval(e.right)
}

// TESTCASE NUMBER: 2
sealed class Shape(val id: Int)

class Circle(id: Int, val r: Int) : Shape(id)

class Rect(id: Int, val w: Int, val h: Int) : Shape(id)

fun area(s: Shape): Int = when (s) {
    is Circle -> s.r * s.r
    is Rect -> s.w * s.h
}

// TESTCASE NUMBER: 3
sealed interface Token {
    val text: String
}

class Word(override val text: String) : Token

class Symbol(override val text: String, val code: Int) : Token

fun describe(t: Token): String = when (t) {
    is Word -> "w:${t.text}"
    is Symbol -> "s:${t.text}:${t.code}"
}

fun box(): String {
    if (eval(Num(1)) != 1) return "NOK: num"
    if (eval(Sum(Num(2), Num(3))) != 5) return "NOK: sum"
    if (eval(Sum(Sum(Num(1), Num(1)), Num(2))) != 4) return "NOK: nested-sum"

    if (area(Circle(1, 3)) != 9) return "NOK: circle"
    if (area(Rect(2, 4, 5)) != 20) return "NOK: rect"
    if (Circle(7, 1).id != 7) return "NOK: circle-id"

    if (describe(Word("hi")) != "w:hi") return "NOK: word"
    if (describe(Symbol("+", 43)) != "s:+:43") return "NOK: symbol"
    return "OK"
}
