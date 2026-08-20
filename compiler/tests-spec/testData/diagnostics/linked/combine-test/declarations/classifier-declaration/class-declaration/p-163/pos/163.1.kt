// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 163 -> sentence 163
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 163 -> sentence 163
 *                inheritance, inheriting -> paragraph 163 -> sentence 163
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 163 -> sentence 163
 * NUMBER: 1
 * DESCRIPTION: type inference for same-file sealed inheritors with constructor delegation and exhaustive when in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Expr

data class Num(val n: Int) : Expr()

data class Sum(val left: Expr, val right: Expr) : Expr()

fun case1() {
    val e: Expr = Num(1)
    e checkType { check<Expr>() }
    Num(2) checkType { check<Num>() }
    checkSubtype<Expr>(Sum(Num(1), Num(2)))
    val n = when (e) {
        is Num -> e.n
        is Sum -> 0
    }
    n checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
sealed class Shape(val id: Int)

class Circle(id: Int, val r: Int) : Shape(id)

class Rect(id: Int, val w: Int, val h: Int) : Shape(id)

fun case2() {
    val c = Circle(1, 3)
    c checkType { check<Circle>() }
    checkSubtype<Shape>(c)
    c.id checkType { check<Int>() }
    c.r checkType { check<Int>() }
    val s: Shape = Rect(2, 4, 5)
    s checkType { check<Shape>() }
}

// TESTCASE NUMBER: 3
sealed interface Token {
    val text: String
}

class Word(override val text: String) : Token

class Symbol(override val text: String, val code: Int) : Token

fun case3() {
    val t: Token = Word("hi")
    t checkType { check<Token>() }
    Word("a") checkType { check<Word>() }
    checkSubtype<Token>(Symbol("+", 43))
    Symbol("+", 43).code checkType { check<Int>() }
}
