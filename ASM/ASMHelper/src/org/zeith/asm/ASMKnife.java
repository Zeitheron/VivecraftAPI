package org.zeith.asm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import com.zeitheron.hammercore.asm.ObjectWebUtils;

public class ASMKnife
{
	private static final int BUFFER_SIZE = 8192;
	private static final int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8;
	
	public static final Map<String, String> bytecodeReplaces = new HashMap<>();
	
	public static void replace(String mc, String forge)
	{
		bytecodeReplaces.put("L" + mc + ";", "L" + forge.replace('.', '/') + ";");
	}
	
	public static void main(String[] args)
	{
		replace("bhy", "net.minecraft.client.settings.KeyBinding");
		replace("bhe", "net.minecraft.util.math.Vec3d");
		replace("bib", "net.minecraft.client.Minecraft");
		
		File run = new File("run");
		if(!run.isDirectory())
			run.mkdirs();
		
		File lib = new File(run, "minecrift-1.12.2-jrbudda-11-r2.jar");
		
		if(!lib.isFile())
		{
			System.err.println(lib + " not found");
			return;
		}
		
		File tmp = new File(lib.getAbsolutePath() + ".tmp");
		
		try
		{
			Files.copy(lib.toPath(), tmp.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
			
			try(JarFile jf = new JarFile(lib))
			{
				for(JarEntry je : jf.stream().filter(e -> e.getName().startsWith("org/vivecraft/") && e.getName().endsWith(".class")).collect(Collectors.toList()))
				{
					byte[] data = null;
					try(InputStream in = jf.getInputStream(je))
					{
						data = read(in, (int) je.getSize());
					}
					if(data != null)
					{
						ClassNode node = ObjectWebUtils.loadClass(data);
						
						List<MethodNode> methods = node.methods;
						for(MethodNode mn : methods)
						{
							String prev = mn.desc;
							mn.desc = processReplaces(mn.desc);
							if(!mn.desc.equals(prev))
								System.out.println("Changed desc in <METHOD> " + mn.name + " from " + prev + " to " + mn.desc);
							
							if(mn.signature != null)
							{
								prev = mn.signature;
								mn.signature = processReplaces(mn.signature);
								if(!mn.signature.equals(prev))
									System.out.println("Changed signature in <METHOD> " + mn.name + " from " + prev + " to " + mn.signature);
							}
						}
						
						List<FieldNode> fields = node.fields;
						for(FieldNode fn : fields)
						{
							String prev = fn.desc;
							fn.desc = processReplaces(fn.desc);
							if(!fn.desc.equals(prev))
								System.out.println("Changed desc in <FIELD> " + fn.name + " from " + prev + " to " + fn.desc);
							
							if(fn.signature != null)
							{
								prev = fn.signature;
								fn.signature = processReplaces(fn.signature);
								if(!fn.signature.equals(prev))
									System.out.println("Changed signature in <FIELD> " + fn.name + " from " + prev + " to " + fn.signature);
							}
						}
						
						File target = new File(run, je.getName());
						target.mkdirs();
						target.delete();
						
						ObjectWebUtils.writeClassToFile(node, target);
					}
				}
			}
		} catch(Throwable err)
		{
			if(tmp.isFile())
				try
				{
					Files.copy(tmp.toPath(), lib.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
					tmp.delete();
				} catch(Throwable err2)
				{
					err2.printStackTrace();
				}
			
			err.printStackTrace();
		}
	}
	
	private static String processReplaces(String desc)
	{
		for(Entry<String, String> replace : bytecodeReplaces.entrySet())
			desc = desc.replaceAll(replace.getKey(), replace.getValue());
		return desc;
	}
	
	/**
	 * Reads all the bytes from an input stream. Uses {@code initialSize} as a
	 * hint about how many bytes the stream will have.
	 *
	 * @param source
	 *            the input stream to read from
	 * @param initialSize
	 *            the initial size of the byte array to allocate
	 * 			
	 * @return a byte array containing the bytes read from the file
	 * 			
	 * @throws IOException
	 *             if an I/O error occurs reading from the stream
	 * @throws OutOfMemoryError
	 *             if an array of the required size cannot be allocated
	 */
	private static byte[] read(InputStream source, int initialSize) throws IOException
	{
		int capacity = initialSize;
		byte[] buf = new byte[capacity];
		int nread = 0;
		int n;
		for(;;)
		{
			// read to EOF which may read more or less than initialSize (eg:
			// file
			// is truncated while we are reading)
			while((n = source.read(buf, nread, capacity - nread)) > 0)
				nread += n;
				
			// if last call to source.read() returned -1, we are done
			// otherwise, try to read one more byte; if that failed we're done
			// too
			if(n < 0 || (n = source.read()) < 0)
				break;
			
			// one more byte was read; need to allocate a larger buffer
			if(capacity <= MAX_BUFFER_SIZE - capacity)
			{
				capacity = Math.max(capacity << 1, BUFFER_SIZE);
			} else
			{
				if(capacity == MAX_BUFFER_SIZE)
					throw new OutOfMemoryError("Required array size too large");
				capacity = MAX_BUFFER_SIZE;
			}
			buf = Arrays.copyOf(buf, capacity);
			buf[nread++] = (byte) n;
		}
		return (capacity == nread) ? buf : Arrays.copyOf(buf, nread);
	}
}