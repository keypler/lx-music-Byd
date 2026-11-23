const base64Chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'

export const btoa = (data: string): string => {
  let output = ''
  for (let i = 0; i < data.length; i += 3) {
    const hasB2 = i + 1 < data.length
    const hasB3 = i + 2 < data.length
    const b1 = data.charCodeAt(i) & 0xff
    const b2 = hasB2 ? data.charCodeAt(i + 1) & 0xff : 0
    const b3 = hasB3 ? data.charCodeAt(i + 2) & 0xff : 0
    output += base64Chars.charAt(b1 >> 2)
    output += base64Chars.charAt(((b1 & 3) << 4) | (b2 >> 4))
    output += hasB2 ? base64Chars.charAt(((b2 & 15) << 2) | (b3 >> 6)) : '='
    output += hasB3 ? base64Chars.charAt(b3 & 63) : '='
  }
  return output
}
