// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 278 -> sentence 278
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 278 -> sentence 278
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 278 -> sentence 278
 * NUMBER: 1
 * DESCRIPTION: an internal primary constructor is invisible from another module (INVISIBLE_REFERENCE); covers plain class, generic class, and call from another top-level class; contrasts with p-84 same-module success and with declaration-visibility p-4 top-level internal
 */

// MODULE: libModule
// FILE: Lib.kt
package libModule

class Api internal constructor(val code: Int)

class Box<T> internal constructor(val value: T)

class Token internal constructor(val id: Int)

// MODULE: mainModule(libModule)
// FILE: Main.kt
package mainModule

import libModule.Api
import libModule.Box
import libModule.Token

// TESTCASE NUMBER: 1
fun case1() {
    <!INVISIBLE_REFERENCE!>Api<!>(1)
}

// TESTCASE NUMBER: 2
fun case2() {
    <!INVISIBLE_REFERENCE!>Box<!>("x")
}

// TESTCASE NUMBER: 3
class TokenClient {
    fun make() {
        <!INVISIBLE_REFERENCE!>Token<!>(7)
    }
}
