// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 172 -> sentence 172
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 172 -> sentence 172
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 172 -> sentence 172
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 172 -> sentence 172
 * NUMBER: 1
 * DESCRIPTION: type inference for superclass then subclass init after constructor delegation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base {
    val buf = StringBuilder()
    init {
        buf.append('B')
    }
}

class Child : Base() {
    init {
        buf.append('C')
    }
}

fun case1() {
    val c = Child()
    c checkType { check<Child>() }
    checkSubtype<Base>(c)
    c.buf checkType { check<StringBuilder>() }
    c.buf.toString() checkType { check<String>() }
}

// TESTCASE NUMBER: 2
open class Tagged(tag: Char) {
    val buf = StringBuilder()
    init {
        buf.append(tag)
    }
}

class TaggedChild(c: Char) : Tagged(c) {
    init {
        buf.append('C')
    }
}

fun case2() {
    val t = TaggedChild('B')
    t checkType { check<TaggedChild>() }
    checkSubtype<Tagged>(t)
    t.buf checkType { check<StringBuilder>() }
}

// TESTCASE NUMBER: 3
open class Grand {
    val buf = StringBuilder()
    init {
        buf.append('G')
    }
}

open class Mid(v: Int) : Grand() {
    init {
        buf.append('M')
        buf.append(v.toString())
    }
}

class Leaf(v: Int) : Mid(v) {
    init {
        buf.append('L')
    }
}

fun case3() {
    val leaf = Leaf(2)
    leaf checkType { check<Leaf>() }
    checkSubtype<Mid>(leaf)
    checkSubtype<Grand>(leaf)
    leaf.buf checkType { check<StringBuilder>() }
}
