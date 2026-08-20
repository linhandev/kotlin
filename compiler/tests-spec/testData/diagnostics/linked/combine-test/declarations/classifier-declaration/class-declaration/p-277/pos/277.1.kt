// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 277 -> sentence 277
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 277 -> sentence 277
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 277 -> sentence 277
 *                declarations, classifier-declaration, classifier-declaration-scopes -> paragraph 277 -> sentence 277
 * NUMBER: 1
 * DESCRIPTION: precise types when companion factories construct classes with private primary constructors
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Vault private constructor(val v: Int) {
    companion object {
        fun open(): Vault = Vault(1)
        fun open(v: Int): Vault = Vault(v)
    }
}

fun case1() {
    val v = Vault.open(3)
    v checkType { check<Vault>() }
    v.v checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
class Box<T> private constructor(val value: T) {
    companion object {
        fun of(value: String): Box<String> = Box(value)
    }
}

fun case2() {
    val b = Box.of("ok")
    b checkType { check<Box<String>>() }
    b.value checkType { check<String>() }
}

// TESTCASE NUMBER: 3
class Token private constructor(val code: Int, val tag: String) {
    companion object {
        fun numbered(code: Int): Token = Token(code, "n")
        fun tagged(tag: String): Token = Token(0, tag)
    }
}

fun case3() {
    val t = Token.numbered(9)
    t checkType { check<Token>() }
    t.code checkType { check<Int>() }
    t.tag checkType { check<String>() }
}
